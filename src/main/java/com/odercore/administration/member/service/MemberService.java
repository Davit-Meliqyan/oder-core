package com.odercore.administration.member.service;

import com.odercore.administration.member.dto.request.patch.MemberPatchDto;
import com.odercore.administration.member.dto.request.upsert.MemberUpsertDto;
import com.odercore.administration.member.dto.response.MemberDto;
import com.odercore.administration.member.entity.Member;
import com.odercore.administration.member_role.MemberRole;
import com.odercore.administration.member_role.MemberRoleRepository;
import com.odercore.common.mapper.AbstractMapper;
import com.odercore.common.service.AbstractCrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class MemberService extends AbstractCrudService<
        Member,
        MemberDto,
        MemberUpsertDto,
        MemberPatchDto
        > {

    private final MemberRoleRepository memberRoleRepository;

    protected MemberService(JpaRepository<Member, UUID> repository,
                            AbstractMapper<Member, MemberDto, MemberUpsertDto> mapper,
                            MemberRoleRepository memberRoleRepository) {
        super(repository, mapper);
        this.memberRoleRepository = memberRoleRepository;
    }


    @Override
    protected void afterSaving(MemberUpsertDto dto, Member entity) {
        if (entity.getId() != null) {
            memberRoleRepository.deleteByMemberId(entity.getId());
        }

        if (dto.getRoles() != null) {
            for (UUID roleId : dto.getRoles()) {
                MemberRole mr = MemberRole.builder()
                        .memberId(entity.getId())
                        .roleId(roleId)
                        .build();
                memberRoleRepository.save(mr);
            }
        }
    }

    protected void mapUpdateToEntity(MemberUpsertDto updateDto, Member entity) {
        entity.setName(updateDto.getName());
        entity.setSureName(updateDto.getSureName());
        entity.setPhone(updateDto.getPhone());
        entity.setEmail(updateDto.getEmail());
        entity.setPosition(updateDto.getPosition());
    }

}
