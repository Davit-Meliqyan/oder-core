package com.odercore.administration.company.service;

import com.odercore.administration.company.dto.request.CompanyLicenseCreateDto;
import com.odercore.administration.company.dto.response.CompanyLicenseDto;
import com.odercore.administration.company.entity.CompanyLicense;
import com.odercore.administration.company.mapper.CompanyLicenseMapper;
import com.odercore.administration.company.repository.CompanyLicenseRepository;
import com.odercore.common.utils.FileUtils;
import com.odercore.license.LicenseProcessor;
import com.odercore.minio.MinioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CompanyLicenseService {

    private final CompanyLicenseRepository licenseRepository;
    private final CompanyLicenseMapper licenseMapper;
    private final MinioService minioService;
    private final LicenseProcessor licenseProcessor;

    @Value("${minio.bucket}")
    private String bucket;

    public CompanyLicenseService(
            CompanyLicenseRepository licenseRepository,
            CompanyLicenseMapper licenseMapper,
            MinioService minioService,
            LicenseProcessor licenseProcessor) {
        this.licenseRepository = licenseRepository;
        this.licenseMapper = licenseMapper;
        this.minioService = minioService;
        this.licenseProcessor = licenseProcessor;
    }

    public List<CompanyLicenseDto> getAll() {
        return licenseRepository.findAll()
                .stream()
                .map(licenseMapper::toDto)
                .toList();
    }

    public CompanyLicenseDto getById(UUID id) {
        CompanyLicense license = getLicenseOrThrow(id);
        return licenseMapper.toDto(license);

    }

    public CompanyLicenseDto create(CompanyLicenseCreateDto createDto) {
        CompanyLicense license = licenseMapper.toEntity(createDto);
        license.setActive(true);
        license.setFileURLs(new ArrayList<>());
        return licenseMapper.toDto(licenseRepository.save(license));
    }

    public CompanyLicenseDto update(UUID id, CompanyLicenseCreateDto updateDto) {
        return licenseRepository.findById(id)
                .map(license -> {
                    license.setName(updateDto.getName());
                    license.setDescription(updateDto.getDescription());
                    license.setOrganizationIssued(updateDto.getOrganizationIssued());
                    license.setDate(updateDto.getDate());
                    license.setDateOfExpiry(updateDto.getDateOfExpiry());
                    license.setExpiryReminder(updateDto.getExpiryReminder());
                    return licenseMapper.toDto(licenseRepository.save(license));
                })
                .orElseThrow(() -> new RuntimeException("License not found"));
    }

    @Transactional
    public void delete(UUID id) {
        CompanyLicense license = getLicenseOrThrow(id);
        licenseRepository.deleteById(id);

        try {
            if (license.getFileURLs() != null) {
                for (String fileName : license.getFileURLs()) {
                    minioService.deleteFile(bucket, fileName);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file from MinIO, rollback DB changes", e);
        }
    }

    @Transactional
    public CompanyLicenseDto uploadFiles(UUID licenseId, List<MultipartFile> files) throws Exception {
        CompanyLicense license = getLicenseOrThrow(licenseId);
        FileUtils.uploadFilesToLicense(license, licenseId, files, minioService, bucket, "License");
        licenseRepository.save(license);
        return licenseMapper.toDto(license);
    }

    @Transactional
    public void deleteFileFromLicense(UUID licenseId, String fileName) {
        CompanyLicense license = getLicenseOrThrow(licenseId);
        minioService.deleteFile(bucket, fileName);
        license.getFileURLs().remove(fileName);
        licenseRepository.save(license);
    }

    private CompanyLicense getLicenseOrThrow(UUID licenseId) {
        return licenseRepository.findById(licenseId)
                .orElseThrow(() -> new RuntimeException("License not found"));
    }

    public void dailyCheck() {
        List<CompanyLicense> companyLicenses = licenseRepository.findAll();
        licenseProcessor.processLicenses(companyLicenses);
        licenseRepository.saveAll(companyLicenses);
    }

}
