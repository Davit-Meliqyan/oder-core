package com.odercore.company.service;

import com.odercore.company.dto.response.CompanyLicenseDto;
import com.odercore.company.mapper.CompanyLicenseMapper;
import com.odercore.company.repository.CompanyLicenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyLicenseService {

    private final CompanyLicenseRepository licenseRepository;
    private final CompanyLicenseMapper licenseMapper;

    public CompanyLicenseService(CompanyLicenseRepository licenseRepository, CompanyLicenseMapper licenseMapper) {
        this.licenseRepository = licenseRepository;
        this.licenseMapper = licenseMapper;
    }

    public List<CompanyLicenseDto> getAll() {
        return licenseRepository.findAll()
                .stream()
                .map(licenseMapper::toDto)
                .toList();
    }

}
