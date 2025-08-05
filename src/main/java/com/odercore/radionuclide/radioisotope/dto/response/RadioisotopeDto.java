package com.odercore.radionuclide.radioisotope.dto.response;

import com.odercore.common.dto.BaseDto;
import com.odercore.radionuclide.enums.Aim;
import lombok.*;

import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RadioisotopeDto extends BaseDto {
    private String name;
    private String shortName;
    private Integer halfLife;
    private TimeUnit halfLifeUnite;
    private Aim aim;
}
