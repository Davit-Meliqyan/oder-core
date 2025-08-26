package com.odercore.administration.member.dto.request.upsert;


import com.odercore.common.enums.DurationUnit;
import com.odercore.common.enums.ExpiryReminder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLicenseCreateDto {
    private String name;
    private String description;
    private String organizationIssued;
    private LocalDateTime date;
    private LocalDateTime dateOfExpiry;
    private ExpiryReminder expiryReminder;
    private Integer trainingDurationValue;
    private DurationUnit trainingDurationUnit;
}
