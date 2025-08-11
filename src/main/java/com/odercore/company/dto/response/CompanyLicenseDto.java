package com.odercore.company.dto.response;

import com.odercore.common.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompanyLicenseDto extends BaseDto {
    private String name;
    private String description;
    private String organizationIssued;
    private LocalDateTime date;
    private LocalDateTime dateOfExpiry;
    private Boolean active;
}
