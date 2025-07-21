package com.odercore.equipment.hotlab.dto.response;

import com.odercore.equipment.dto.response.EquipmentDto;
import com.odercore.equipment.hotlab.enums.CabinetType;
import com.odercore.equipment.hotlab.enums.CleanClassification;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class HotLabDto extends EquipmentDto {

    private CleanClassification cleanClassification;
    private CabinetType cabinetType;

}
