package com.odercore.company.service;

import com.odercore.company.dto.request.CompanyLicenseCreateDto;
import com.odercore.company.dto.response.CompanyLicenseDto;
import com.odercore.company.entity.CompanyLicense;
import com.odercore.company.mapper.CompanyLicenseMapper;
import com.odercore.company.repository.CompanyLicenseRepository;
import com.odercore.minio.MinioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CompanyLicenseService {

    private final CompanyLicenseRepository licenseRepository;
    private final CompanyLicenseMapper licenseMapper;
    private final MinioService minioService;

    @Value("${minio.bucket}")
    private String bucket;

    public CompanyLicenseService(CompanyLicenseRepository licenseRepository, CompanyLicenseMapper licenseMapper, MinioService minioService) {
        this.licenseRepository = licenseRepository;
        this.licenseMapper = licenseMapper;
        this.minioService = minioService;
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
        int startIndex = getNextFileIndex(license);

        for (int i = 0; i < files.size(); i++) {
            MultipartFile file = files.get(i);
            String fileName = buildFileName(licenseId, file, startIndex, i);
            minioService.uploadFile(bucket, fileName, file);
            license.getFileURLs().add(fileName);
        }

        licenseRepository.save(license);
        return licenseMapper.toDto(license);
    }

    private CompanyLicense getLicenseOrThrow(UUID licenseId) {
        return licenseRepository.findById(licenseId)
                .orElseThrow(() -> new RuntimeException("License not found"));
    }

    private int getNextFileIndex(CompanyLicense license) {
        if (license.getFileURLs() == null || license.getFileURLs().isEmpty()) {
            return 0;
        }

        TreeSet<Integer> usedIndexes = license.getFileURLs().stream()
                .map(this::extractFileIndex)
                .collect(Collectors.toCollection(TreeSet::new));

        if (!usedIndexes.contains(0)) {
            return 0;
        }

        int index = 1;
        while (usedIndexes.contains(index)) {
            index++;
        }
        return index;
    }

    private int extractFileIndex(String fileName) {
        int openIdx = fileName.lastIndexOf('(');
        int closeIdx = fileName.lastIndexOf(')');
        if (openIdx != -1 && closeIdx != -1 && closeIdx > openIdx) {
            try {
                return Integer.parseInt(fileName.substring(openIdx + 1, closeIdx));
            } catch (NumberFormatException ignored) {
            }
        }
        return 0;
    }

    private String buildFileName(UUID licenseId, MultipartFile file, int startIndex, int currentIndex) {
        String extension = getFileExtension(file);
        if (startIndex == 0 && currentIndex == 0) {
            return "License-" + licenseId + extension;
        }
        return "License-" + licenseId + "(" + (startIndex + currentIndex) + ")" + extension;
    }

    private String getFileExtension(MultipartFile file) {
        String originalName = file.getOriginalFilename();
        if (originalName != null && originalName.contains(".")) {
            return originalName.substring(originalName.lastIndexOf('.'));
        }
        return "";
    }

    @Transactional
    public void deleteFileFromLicense(UUID licenseId, String fileName) {
        CompanyLicense license = getLicenseOrThrow(licenseId);
        minioService.deleteFile(bucket, fileName);
        license.getFileURLs().remove(fileName);
        licenseRepository.save(license);
    }

}
