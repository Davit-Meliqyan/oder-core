package com.odercore.radionuclide.radiopharmaceutical.dto.request.upsert;

import com.odercore.radionuclide.enums.Aim;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RadiopharmaceuticalUpsertDto {

    private String name;
    private String shortName;
    private Aim aim;
    private UUID radioisotopeId;

}
