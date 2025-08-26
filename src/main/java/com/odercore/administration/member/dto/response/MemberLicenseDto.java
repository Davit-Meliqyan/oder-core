package com.odercore.administration.member.dto.response;

import com.odercore.common.dto.BaseDto;
import com.odercore.common.enums.DurationUnit;
import com.odercore.common.enums.ExpiryReminder;
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
public class MemberLicenseDto extends BaseDto {
    private String name;
    private String description;
    private String organizationIssued;
    private LocalDateTime date;
    private LocalDateTime dateOfExpiry;
    private ExpiryReminder expiryReminder;
    private Integer trainingDurationValue;
    private DurationUnit trainingDurationUnit;
    private Boolean active;
    private List<String> fileURLs;
}