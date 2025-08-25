package com.odercore.administration.company.dto.request;

import lombok.Data;

@Data
public class CompanyUpdateDto {
    private String name;
    private String shortName;
    private String address;
    private String phoneNumber;
    private String email;
    private String website;
}
