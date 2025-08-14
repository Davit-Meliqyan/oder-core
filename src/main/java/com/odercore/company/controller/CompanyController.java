package com.odercore.company.controller;

import com.odercore.company.dto.request.CompanyCreateDto;
import com.odercore.company.dto.request.CompanyLicenseCreateDto;
import com.odercore.company.dto.request.CompanyUpdateDto;
import com.odercore.company.dto.response.CompanyDto;
import com.odercore.company.dto.response.CompanyLicenseDto;
import com.odercore.company.service.CompanyLicenseService;
import com.odercore.company.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/company")
@Tag(name = "Company", description = "Company Management")
public class CompanyController {

    private final CompanyService companyService;
    private final CompanyLicenseService licenseService;

    public CompanyController(
            CompanyService companyService,
            CompanyLicenseService licenseService) {
        this.companyService = companyService;
        this.licenseService = licenseService;
    }

    @GetMapping
    public ResponseEntity<CompanyDto> getCompany() {
        return ResponseEntity.ok(companyService.getCompany());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CompanyCreateDto createDto) {
        try {
            return ResponseEntity.ok(companyService.createCompany(createDto));
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());

        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyDto> update(@PathVariable UUID id, @RequestBody CompanyUpdateDto updateDto) {
        try {
            return ResponseEntity.ok(companyService.update(id, updateDto));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping(value = "/logo", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload company logo")
    public ResponseEntity<String> uploadLogo(
            @Parameter(description = "Logo file", required = true)
            @RequestParam("file") MultipartFile file
    ) {
        try {
            companyService.uploadLogo(file);
            return ResponseEntity.ok("Logo uploaded");
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Upload failed: " + e.getMessage());
        }
    }

    @DeleteMapping("/logo")
    public ResponseEntity<String> deleteLogo() {
        companyService.deleteLogo();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/license")
    public ResponseEntity<List<CompanyLicenseDto>> getAll() {
        return ResponseEntity.ok(licenseService.getAll());
    }

    @GetMapping("/license/{licenseId}")
    public CompanyLicenseDto getById(@PathVariable UUID licenseId) {
        return licenseService.getById(licenseId);
    }

    @PostMapping("/license")
    public CompanyLicenseDto create(@RequestBody CompanyLicenseCreateDto createDto) {
        return licenseService.create(createDto);
    }

    @PutMapping("/license/{licenseId}")
    public CompanyLicenseDto update(@PathVariable UUID licenseId, @RequestBody CompanyLicenseCreateDto updateDto) {
        return licenseService.update(licenseId, updateDto);
    }

    @DeleteMapping("/license/{licenseId}")
    public ResponseEntity<Void> delete(@PathVariable UUID licenseId) {
        licenseService.delete(licenseId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "license/{licenseId}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload files for a company license")
    public ResponseEntity<CompanyLicenseDto> uploadLicenseFiles(
            @PathVariable("licenseId") UUID id,
            @Parameter(description = "Files to upload", required = true)
            @RequestPart("files")
            MultipartFile[] files
    ) {
        try {
            CompanyLicenseDto updatedLicense = licenseService.uploadFiles(id, Arrays.asList(files));
            return ResponseEntity.ok(updatedLicense);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @DeleteMapping("/licenses/{licenseId}/{fileName}")
    public ResponseEntity<Void> deleteLicenseFile(
            @PathVariable UUID licenseId,
            @PathVariable String fileName
    ) {
        licenseService.deleteFileFromLicense(licenseId, fileName);
        return ResponseEntity.noContent().build();
    }

}

