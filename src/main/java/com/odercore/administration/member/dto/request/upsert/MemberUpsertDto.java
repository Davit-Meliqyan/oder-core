package com.odercore.administration.member.dto.request.upsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberUpsertDto {

    private String name;
    private String sureName;
    private String phone;
    private String email;
    private String position;
    private Set<UUID> roles;

}
