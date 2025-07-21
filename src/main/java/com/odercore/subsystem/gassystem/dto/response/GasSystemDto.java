package com.odercore.subsystem.gassystem.dto.response;

import com.odercore.common.dto.BaseDto;
import com.odercore.equipment.enums.EquipmentType;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class GasSystemDto extends BaseDto {

    private String gasName;
    private BigDecimal purity;
    private Set<EquipmentType> usedFor;

}
