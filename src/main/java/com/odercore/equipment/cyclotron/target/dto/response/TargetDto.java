package com.odercore.equipment.cyclotron.target.dto.response;

import com.odercore.common.dto.BaseDto;
import com.odercore.equipment.cyclotron.target.enums.TargetType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TargetDto extends BaseDto {

    private String name;
    private TargetType targetType;
    private BigDecimal targetPressure;
    private BigDecimal targetCurrent;

    private UUID cyclotronId;
    private UUID radioisotopeId;
    private String radioisotopeName;

}
