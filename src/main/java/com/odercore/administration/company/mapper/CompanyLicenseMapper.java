package com.odercore.administration.company.mapper;

import com.odercore.administration.company.dto.request.CompanyLicenseCreateDto;
import com.odercore.administration.company.dto.response.CompanyLicenseDto;
import com.odercore.administration.company.entity.CompanyLicense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CompanyLicenseMapper {

    public abstract CompanyLicenseDto toDto(CompanyLicense entity);

    public abstract CompanyLicense toEntity(CompanyLicenseCreateDto createDto);
}
