package com.odercore.equipment.dto.request.upsert;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EquipmentUpsertDto {

    private String brand;
    private String model;
    private String serialNumber;

}
