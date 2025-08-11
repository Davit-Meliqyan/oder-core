package com.odercore.company.controller;

import com.odercore.company.dto.request.CompanyCreateDto;
import com.odercore.company.dto.request.CompanyUpdateDto;
import com.odercore.company.dto.response.CompanyDto;
import com.odercore.company.service.CompanyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@RequestMapping("/api/company")
@Tag(name = "Company", description = "Company Management")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
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

    @DeleteMapping("/remove")
    public ResponseEntity<String> remove() {
        companyService.remove();
        return ResponseEntity.noContent().build();
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
}

