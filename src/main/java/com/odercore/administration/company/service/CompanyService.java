package com.odercore.administration.company.service;

import com.odercore.administration.company.dto.request.CompanyCreateDto;
import com.odercore.administration.company.dto.request.CompanyUpdateDto;
import com.odercore.administration.company.dto.response.CompanyDto;
import com.odercore.administration.company.entity.Company;
import com.odercore.administration.company.mapper.CompanyMapper;
import com.odercore.administration.company.repository.CompanyRepository;
import com.odercore.minio.MinioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    private final CompanyRepository repository;
    private final CompanyMapper mapper;
    private final MinioService minioService;

    @Value("${minio.bucket}")
    private String bucket;

    public CompanyService(
            CompanyRepository companyRepository,
            CompanyMapper mapper,
            MinioService minioService) {
        this.repository = companyRepository;
        this.mapper = mapper;
        this.minioService = minioService;
    }

    public CompanyDto getCompany() {
        return findCompany()
                .map(mapper::toDto)
                .orElse(null);
    }

    public CompanyDto createCompany(CompanyCreateDto createDto) {
        if (repository.count() > 0) {
            throw new IllegalStateException("Company already exists");
        }
        Company company = mapper.toEntity(createDto);
        return mapper.toDto(repository.save(company));
    }

    public CompanyDto update(UUID id, CompanyUpdateDto updatedDto) {
        return repository.findById(id)
                .map(c -> {
                    c.setName(updatedDto.getName());
                    c.setShortName(updatedDto.getShortName());
                    c.setAddress(updatedDto.getAddress());
                    c.setPhoneNumber(updatedDto.getPhoneNumber());
                    c.setEmail(updatedDto.getEmail());
                    c.setWebsite(updatedDto.getWebsite());
                    c.setLogoURL(c.getLogoURL());
                    return mapper.toDto(repository.save(c));
                }).orElseThrow(() -> new RuntimeException("Company not found"));
    }

    public void remove() {
        Company company = findCompany()
                .orElseThrow(() -> new RuntimeException("Company not found"));
        repository.delete(company);
    }

    public void uploadLogo(MultipartFile file) throws Exception {
        Company company = findCompany()
                .orElseThrow(() -> new RuntimeException("Company not found"));

        if (company.getLogoURL() != null) {
            throw new IllegalStateException("Logo already exists. Delete it before uploading a new one.");
        }

        String objectName = "Company-" + company.getId() + "-logo";

        minioService.uploadFile(bucket, objectName, file);
        company.setLogoURL(objectName);
        repository.save(company);
    }

    public void deleteLogo() {
        Company company = findCompany()
                .orElseThrow(() -> new RuntimeException("Company not found"));

        if (company.getLogoURL() != null) {
            minioService.deleteFile(bucket, company.getLogoURL());
            company.setLogoURL(null);
            repository.save(company);
        }
    }

    private Optional<Company> findCompany() {
        return repository.findAll().stream().findFirst();
    }

}

