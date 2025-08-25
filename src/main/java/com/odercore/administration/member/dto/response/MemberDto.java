package com.odercore.administration.member.dto.response;

import com.odercore.administration.role.dto.response.RoleSlimDto;
import com.odercore.common.dto.BaseDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MemberDto extends BaseDto {

    private String name;
    private String sureName;
    private String phone;
    private String email;
    private String position;
    private Set<RoleSlimDto> roles;

}
