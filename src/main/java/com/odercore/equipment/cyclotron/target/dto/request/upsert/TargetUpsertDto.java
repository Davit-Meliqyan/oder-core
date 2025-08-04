package com.odercore.equipment.cyclotron.target.dto.request.upsert;

import com.odercore.equipment.cyclotron.target.enums.TargetType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetUpsertDto {

    private String name;
    private TargetType targetType;
    private BigDecimal targetPressure;
    private BigDecimal targetCurrent;

    private UUID exitPortId;
    private UUID radioisotopeId;

}
