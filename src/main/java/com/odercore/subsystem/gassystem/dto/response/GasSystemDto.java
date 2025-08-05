package com.odercore.subsystem.gassystem.dto.response;

import com.odercore.common.dto.BaseDto;
import com.odercore.equipment.enums.EquipmentType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class GasSystemDto extends BaseDto {

    private String gasName;
    private BigDecimal purity;
    private Set<EquipmentType> usedFor;

}
