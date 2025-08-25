package com.odercore.administration.member.mapper;

import com.odercore.administration.member.dto.request.upsert.MemberUpsertDto;
import com.odercore.administration.member.dto.response.MemberDto;
import com.odercore.administration.member.entity.Member;
import com.odercore.administration.member_role.MemberRoleRepository;
import com.odercore.administration.role.dto.response.RoleSlimDto;
import com.odercore.common.mapper.AbstractMapper;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class MemberMapper extends AbstractMapper<
        Member,
        MemberDto,
        MemberUpsertDto> {

    @Autowired
    protected MemberRoleRepository memberRoleRepository;

    @Override
    public abstract Member toEntity(MemberUpsertDto upsertDto);

    @Override
    public abstract MemberDto toDto(Member entity);

    @AfterMapping
    protected void mapRoles(Member entity, @MappingTarget MemberDto dto) {
        Set<RoleSlimDto> roles = memberRoleRepository.findRolesByMemberId(entity.getId())
                .stream()
                .map(role -> RoleSlimDto.builder()
                        .id(role.getId())
                        .name(role.getName())
                        .build()
                )
                .collect(Collectors.toSet());

        dto.setRoles(roles);
    }

}
