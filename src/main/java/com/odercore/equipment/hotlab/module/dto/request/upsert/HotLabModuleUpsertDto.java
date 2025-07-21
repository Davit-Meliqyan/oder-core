package com.odercore.equipment.hotlab.module.dto.request.upsert;

import com.odercore.equipment.dto.request.upsert.EquipmentUpsertDto;
import com.odercore.equipment.hotlab.module.enums.ModuleType;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class HotLabModuleUpsertDto extends EquipmentUpsertDto {

    private Set<ModuleType> moduleTypes;

}
