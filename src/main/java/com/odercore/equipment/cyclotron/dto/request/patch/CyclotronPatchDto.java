package com.odercore.equipment.cyclotron.dto.request.patch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CyclotronPatchDto {
    private String name;
}
