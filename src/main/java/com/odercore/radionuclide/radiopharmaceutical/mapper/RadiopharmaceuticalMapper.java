package com.odercore.radionuclide.radiopharmaceutical.mapper;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.radionuclide.radiopharmaceutical.dto.request.upsert.RadiopharmaceuticalUpsertDto;
import com.odercore.radionuclide.radiopharmaceutical.dto.response.RadiopharmaceuticalDto;
import com.odercore.radionuclide.radiopharmaceutical.entity.Radiopharmaceutical;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class RadiopharmaceuticalMapper extends AbstractMapper<
        Radiopharmaceutical,
        RadiopharmaceuticalDto,
        RadiopharmaceuticalUpsertDto> {

    @Override
    @Mapping(source = "radioisotope.id", target = "radioisotopeId")
    public abstract RadiopharmaceuticalDto toDto(Radiopharmaceutical entity);

    @Override
    @Mapping(target = "radioisotope", ignore = true)
    public abstract Radiopharmaceutical toEntity(RadiopharmaceuticalUpsertDto upsertDto);

}

