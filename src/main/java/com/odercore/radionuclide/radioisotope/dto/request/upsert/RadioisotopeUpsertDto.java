package com.odercore.radionuclide.radioisotope.dto.request.upsert;

import com.odercore.radionuclide.enums.Aim;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.concurrent.TimeUnit;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RadioisotopeUpsertDto {
    private String name;
    private String shortName;
    private Integer halfLife;
    private TimeUnit halfLifeUnite;
    private Aim aim;
}
