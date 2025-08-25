package com.odercore.administration.role.mapper;

import com.odercore.administration.role.dto.request.upsert.RoleUpsertDto;
import com.odercore.administration.role.dto.response.RoleDto;
import com.odercore.administration.role.entity.Role;
import com.odercore.common.mapper.AbstractMapper;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RoleMapper extends AbstractMapper<
        Role,
        RoleDto,
        RoleUpsertDto> {

    @Override
    public abstract RoleDto toDto(Role entity);

    @Override
    public abstract Role toEntity(RoleUpsertDto upsertDto);

}

