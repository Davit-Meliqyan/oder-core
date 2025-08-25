package com.odercore.administration.company.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyCreateDto {
    private String name;
    private String shortName;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;
}
