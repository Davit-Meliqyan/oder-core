package com.odercore.equipment.cyclotron.dto.response;

import com.odercore.equipment.dto.response.EquipmentDto;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class CyclotronDto extends EquipmentDto {

    private BigDecimal beamEnergy;
    private Integer sourcesCount;
    private Integer exitPortsCount;

    private Boolean hasWaterCooling;
    private Boolean hasHeliumCooling;
    private Boolean hasAirCompressor;

}
