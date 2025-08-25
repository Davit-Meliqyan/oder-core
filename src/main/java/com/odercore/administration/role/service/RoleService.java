package com.odercore.administration.role.service;

import com.odercore.administration.member_role.MemberRoleRepository;
import com.odercore.administration.role.dto.request.patch.RolePatchDto;
import com.odercore.administration.role.dto.request.upsert.RoleUpsertDto;
import com.odercore.administration.role.dto.response.RoleDto;
import com.odercore.administration.role.entity.Role;
import com.odercore.common.mapper.AbstractMapper;
import com.odercore.common.service.AbstractCrudService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class RoleService extends AbstractCrudService<
        Role,
        RoleDto,
        RoleUpsertDto,
        RolePatchDto
        > {

    private final MemberRoleRepository memberRoleRepository;

    protected RoleService(JpaRepository<Role, UUID> repository,
                          AbstractMapper<Role, RoleDto, RoleUpsertDto> mapper,
                          MemberRoleRepository memberRoleRepository) {
        super(repository, mapper);
        this.memberRoleRepository = memberRoleRepository;
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        memberRoleRepository.deleteByRoleId(id);
        super.delete(id);
    }

}
