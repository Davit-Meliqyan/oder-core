package com.odercore.company.mapper;

import com.odercore.company.dto.request.CompanyLicenseCreateDto;
import com.odercore.company.dto.response.CompanyLicenseDto;
import com.odercore.company.entity.CompanyLicense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CompanyLicenseMapper {

    public abstract CompanyLicenseDto toDto(CompanyLicense entity);

    public abstract CompanyLicense toEntity(CompanyLicenseCreateDto createDto);
}
