package com.odercore.administration.role.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class RoleSlimDto {

    private UUID id;
    private String name;

}
