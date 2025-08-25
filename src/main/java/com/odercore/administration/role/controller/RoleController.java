package com.odercore.administration.role.controller;

import com.odercore.administration.role.dto.request.patch.RolePatchDto;
import com.odercore.administration.role.dto.request.upsert.RoleUpsertDto;
import com.odercore.administration.role.dto.response.RoleDto;
import com.odercore.administration.role.entity.Role;
import com.odercore.common.controller.AbstractCrudController;
import com.odercore.common.service.AbstractCrudService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/roles")
@Tag(name = "Roles", description = "Roles Management")
public class RoleController extends AbstractCrudController<
        Role,
        RoleDto,
        RoleUpsertDto,
        RolePatchDto
        > {

    protected RoleController(
            AbstractCrudService<
                    Role,
                    RoleDto,
                    RoleUpsertDto,
                    RolePatchDto> service) {
        super(service);
    }
}
