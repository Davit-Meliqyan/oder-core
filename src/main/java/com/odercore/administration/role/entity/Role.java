package com.odercore.administration.role.entity;

import com.odercore.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role extends BaseEntity {

    @Column(name = "name", nullable = false)
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
