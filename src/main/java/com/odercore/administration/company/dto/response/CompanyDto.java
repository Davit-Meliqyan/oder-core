package com.odercore.administration.company.dto.response;

import com.odercore.common.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CompanyDto extends BaseDto {
    private String name;
    private String shortName;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;
    private String logoURL;
}
