package com.odercore.administration.company.mapper;

import com.odercore.administration.company.dto.request.CompanyCreateDto;
import com.odercore.administration.company.dto.response.CompanyDto;
import com.odercore.administration.company.entity.Company;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CompanyMapper {

    public abstract CompanyDto toDto(Company entity);

    public abstract Company toEntity(CompanyCreateDto createDto);

}
