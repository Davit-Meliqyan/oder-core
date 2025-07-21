package com.odercore.radionuclide.radiopharmaceutical.dto.request.patch;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RadiopharmaceuticalPatchDto {
    private String name;
}
