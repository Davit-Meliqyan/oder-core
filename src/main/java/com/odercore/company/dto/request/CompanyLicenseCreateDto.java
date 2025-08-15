package com.odercore.company.dto.request;

import com.odercore.company.enums.ExpiryReminder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyLicenseCreateDto {
    private String name;
    private String description;
    private String organizationIssued;
    private LocalDateTime date;
    private LocalDateTime dateOfExpiry;
    ExpiryReminder expiryReminder;
}
