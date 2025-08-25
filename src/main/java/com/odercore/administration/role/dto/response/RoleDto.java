package com.odercore.administration.role.dto.response;

import com.odercore.common.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RoleDto extends BaseDto {

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
