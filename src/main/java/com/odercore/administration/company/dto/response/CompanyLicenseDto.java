package com.odercore.administration.company.dto.response;

import com.odercore.common.dto.BaseDto;
import com.odercore.administration.company.enums.ExpiryReminder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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
    private ExpiryReminder expiryReminder;
    private Boolean active;
    private List<String> fileURLs;
}
