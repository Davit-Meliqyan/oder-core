package com.odercore.administration.role.dto.request.upsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoleUpsertDto {

    private String name;

    // Users permissions
    private boolean viewUsers;
    private boolean editUsers;
    private boolean createUsers;
    private boolean deleteUsers;

    // Roles permissions
    private boolean viewRoles;
    private boolean editRoles;
    private boolean createRoles;
    private boolean deleteRoles;

    // Company permissions
    private boolean viewCompany;
    private boolean editCompany;
    private boolean createCompany;
    private boolean deleteCompany;

}
