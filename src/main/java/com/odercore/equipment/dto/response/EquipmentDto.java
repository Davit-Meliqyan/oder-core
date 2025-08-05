package com.odercore.equipment.dto.response;

import com.odercore.common.dto.BaseDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class EquipmentDto extends BaseDto {

    private String brand;
    private String model;
    private String serialNumber;

}
