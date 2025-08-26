package com.odercore.administration.member.mapper;


import com.odercore.administration.member.dto.request.upsert.MemberLicenseCreateDto;
import com.odercore.administration.member.dto.response.MemberLicenseDto;
import com.odercore.administration.member.entity.MemberLicense;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class MemberLicenseMapper {

    public abstract MemberLicenseDto toDto(MemberLicense entity);

    public abstract MemberLicense toEntity(MemberLicenseCreateDto createDto);
}
