package com.odercore.subsystem.gassystem.dto.request.upsert;

import com.odercore.equipment.enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GasSystemUpsertDto {

    private String gasName;
    private BigDecimal purity;
    private Set<EquipmentType> usedFor;

}
