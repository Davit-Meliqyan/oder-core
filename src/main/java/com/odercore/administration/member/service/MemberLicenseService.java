package com.odercore.administration.member.service;

import com.odercore.administration.member.dto.request.upsert.MemberLicenseCreateDto;
import com.odercore.administration.member.dto.response.MemberLicenseDto;
import com.odercore.administration.member.entity.MemberLicense;
import com.odercore.administration.member.mapper.MemberLicenseMapper;
import com.odercore.administration.member.repository.MemberLicenseRepository;
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
public class MemberLicenseService {

    private final MemberLicenseRepository licenseRepository;
    private final MemberLicenseMapper licenseMapper;
    private final MinioService minioService;
    private final LicenseProcessor licenseProcessor;

    @Value("${minio.bucket}")
    private String bucket;

    public MemberLicenseService(
            MemberLicenseRepository licenseRepository,
            MemberLicenseMapper licenseMapper,
            MinioService minioService,
            LicenseProcessor licenseProcessor) {
        this.licenseRepository = licenseRepository;
        this.licenseMapper = licenseMapper;
        this.minioService = minioService;
        this.licenseProcessor = licenseProcessor;
    }

    public List<MemberLicenseDto> getByMemberId(UUID memberId) {
        return licenseRepository.findAllByMemberId(memberId)
                .stream()
                .map(licenseMapper::toDto)
                .toList();
    }

    public MemberLicenseDto getById(UUID memberId, UUID licenseId) {
        MemberLicense license = getLicenseOrThrow(memberId, licenseId);
        return licenseMapper.toDto(license);
    }

    public MemberLicenseDto create(UUID memberId, MemberLicenseCreateDto createDto) {
        MemberLicense license = licenseMapper.toEntity(createDto);
        license.setMemberId(memberId);
        license.setActive(true);
        license.setFileURLs(new ArrayList<>());
        return licenseMapper.toDto(licenseRepository.save(license));
    }

    public MemberLicenseDto update(UUID memberId, UUID licenseId, MemberLicenseCreateDto updateDto) {
        return licenseRepository.findByIdAndMemberId(licenseId, memberId)
                .map(license -> {
                    license.setName(updateDto.getName());
                    license.setDescription(updateDto.getDescription());
                    license.setOrganizationIssued(updateDto.getOrganizationIssued());
                    license.setDate(updateDto.getDate());
                    license.setDateOfExpiry(updateDto.getDateOfExpiry());
                    license.setExpiryReminder(updateDto.getExpiryReminder());
                    license.setTrainingDurationUnit(updateDto.getTrainingDurationUnit());
                    license.setTrainingDurationValue(updateDto.getTrainingDurationValue());
                    return licenseMapper.toDto(licenseRepository.save(license));
                })
                .orElseThrow(() -> new RuntimeException("License not found for this member"));
    }

    @Transactional
    public void delete(UUID memberId) {
        List<MemberLicense> licenses = licenseRepository.findAllByMemberId(memberId);

        for (MemberLicense license : licenses) {
            List<String> fileNames = license.getFileURLs();
            for (String fileName : fileNames) {
                try {
                    minioService.deleteFile(bucket, fileName);
                } catch (Exception e) {
                    throw new RuntimeException(
                            "Failed to delete file " + fileName + " from MinIO, rollback DB changes",
                            e
                    );
                }
            }
        }
        licenseRepository.deleteAll(licenses);
    }

    @Transactional
    public void delete(UUID memberId, UUID licenseId) {
        MemberLicense license = getLicenseOrThrow(memberId, licenseId);
        try {
            if (license.getFileURLs() != null) {
                for (String fileName : license.getFileURLs()) {
                    minioService.deleteFile(bucket, fileName);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file from MinIO, rollback DB changes", e);
        }
        licenseRepository.deleteByIdAndMemberId(licenseId, memberId);
    }

    @Transactional
    public MemberLicenseDto uploadFiles(UUID memberId, UUID licenseId, List<MultipartFile> files) throws Exception {
        MemberLicense license = getLicenseOrThrow(memberId, licenseId);
        FileUtils.uploadFilesToLicense(license, licenseId, files, minioService, bucket, "License");
        licenseRepository.save(license);
        return licenseMapper.toDto(license);
    }

    @Transactional
    public void deleteFileFromLicense(UUID memberId, UUID licenseId, String fileName) {
        MemberLicense license = getLicenseOrThrow(memberId, licenseId);
        try {
            minioService.deleteFile(bucket, fileName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete file from MinIO, rollback DB changes", e);
        }
        license.getFileURLs().remove(fileName);
        licenseRepository.save(license);
    }

    private MemberLicense getLicenseOrThrow(UUID memberId, UUID licenseId) {
        return licenseRepository.findByIdAndMemberId(licenseId, memberId)
                .orElseThrow(() -> new RuntimeException("License not found for this member"));
    }

    public void dailyCheck() {
        List<MemberLicense> companyLicenses = licenseRepository.findAll();
        licenseProcessor.processLicenses(companyLicenses);
        licenseRepository.saveAll(companyLicenses);
    }

}
