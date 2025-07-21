package com.odercore.equipment.hotlab.dto.request.upsert;

import com.odercore.equipment.dto.request.upsert.EquipmentUpsertDto;
import com.odercore.equipment.hotlab.enums.CabinetType;
import com.odercore.equipment.hotlab.enums.CleanClassification;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class HotLabUpsertDto extends EquipmentUpsertDto {

    private CleanClassification cleanClassification;
    private CabinetType cabinetType;

}
