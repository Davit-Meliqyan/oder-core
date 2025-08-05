package com.odercore.equipment.hotlab.module.dto.response;

import com.odercore.equipment.dto.response.EquipmentDto;
import com.odercore.equipment.hotlab.module.enums.ModuleType;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class HotLabModuleDto extends EquipmentDto {

    private UUID hotLabId;
    private Set<ModuleType> moduleTypes;

}
