package com.odercore.company.mapper;

import com.odercore.company.dto.request.CompanyCreateDto;
import com.odercore.company.dto.response.CompanyDto;
import com.odercore.company.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CompanyMapper {

    public abstract CompanyDto toDto(Company entity);

    public abstract Company toEntity(CompanyCreateDto createDto);

}
