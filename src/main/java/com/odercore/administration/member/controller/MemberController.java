package com.odercore.administration.member.controller;

import com.odercore.administration.member.dto.request.patch.MemberPatchDto;
import com.odercore.administration.member.dto.request.upsert.MemberLicenseCreateDto;
import com.odercore.administration.member.dto.request.upsert.MemberUpsertDto;
import com.odercore.administration.member.dto.response.MemberDto;
import com.odercore.administration.member.dto.response.MemberLicenseDto;
import com.odercore.administration.member.entity.Member;
import com.odercore.administration.member.service.MemberLicenseService;
import com.odercore.common.controller.AbstractCrudController;
import com.odercore.common.service.AbstractCrudService;
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
@RequestMapping("/api/members")
@Tag(name = "Members", description = "Members Management")
public class MemberController extends AbstractCrudController<
        Member,
        MemberDto,
        MemberUpsertDto,
        MemberPatchDto
        > {

    private final MemberLicenseService licenseService;

    protected MemberController(
            AbstractCrudService<
                    Member,
                    MemberDto,
                    MemberUpsertDto,
                    MemberPatchDto> service, MemberLicenseService licenseService) {
        super(service);
        this.licenseService = licenseService;
    }

    @GetMapping("/{memberId}/license")
    public ResponseEntity<List<MemberLicenseDto>> getAllLicense(@PathVariable UUID memberId) {
        return ResponseEntity.ok(licenseService.getByMemberId(memberId));
    }

    @GetMapping("/{memberId}/license/{licenseId}")
    public MemberLicenseDto getLicenseById(
            @PathVariable UUID memberId,
            @PathVariable UUID licenseId) {
        return licenseService.getById(memberId, licenseId);
    }

    @PostMapping("/{memberId}/license")
    public MemberLicenseDto createLicense(
            @PathVariable UUID memberId,
            @RequestBody MemberLicenseCreateDto createDto) {
        return licenseService.create(memberId, createDto);
    }

    @PutMapping("/{memberId}/license/{licenseId}")
    public MemberLicenseDto updateLicense(
            @PathVariable UUID memberId,
            @PathVariable UUID licenseId,
            @RequestBody MemberLicenseCreateDto updateDto) {
        return licenseService.update(memberId, licenseId, updateDto);
    }

    @DeleteMapping("/{memberId}/license/{licenseId}")
    public ResponseEntity<Void> deleteLicense(
            @PathVariable UUID memberId,
            @PathVariable UUID licenseId) {
        licenseService.delete(memberId, licenseId);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(value = "/{memberId}/license/{licenseId}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Upload files for a member license")
    public ResponseEntity<MemberLicenseDto> uploadLicenseFiles(
            @PathVariable UUID memberId,
            @PathVariable("licenseId") UUID id,
            @Parameter(description = "Files to upload", required = true)
            @RequestPart("files")
            MultipartFile[] files
    ) {
        try {
            MemberLicenseDto updatedLicense = licenseService.uploadFiles(memberId, id, Arrays.asList(files));
            return ResponseEntity.ok(updatedLicense);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
    }

    @DeleteMapping("/{memberId}/licenses/{licenseId}/{fileName}")
    public ResponseEntity<Void> deleteLicenseFile(
            @PathVariable UUID memberId,
            @PathVariable UUID licenseId,
            @PathVariable String fileName
    ) {
        licenseService.deleteFileFromLicense(memberId, licenseId, fileName);
        return ResponseEntity.noContent().build();
    }
}
