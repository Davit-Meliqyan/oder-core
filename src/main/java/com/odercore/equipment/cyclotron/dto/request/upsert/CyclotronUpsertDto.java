package com.odercore.equipment.cyclotron.dto.request.upsert;

import com.odercore.equipment.dto.request.upsert.EquipmentUpsertDto;
import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode(callSuper = true)
public class CyclotronUpsertDto extends EquipmentUpsertDto {

    private String name;
    private BigDecimal beamEnergy;
    private Integer sourcesCount;
    private Integer exitPortsCount;

}
