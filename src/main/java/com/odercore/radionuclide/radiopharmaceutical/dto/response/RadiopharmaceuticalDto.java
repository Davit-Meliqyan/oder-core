package com.odercore.radionuclide.radiopharmaceutical.dto.response;

import com.odercore.common.dto.BaseDto;
import com.odercore.radionuclide.enums.Aim;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RadiopharmaceuticalDto extends BaseDto {

    private String name;
    private String shortName;
    private Aim aim;
    private UUID radioisotopeId;

}
