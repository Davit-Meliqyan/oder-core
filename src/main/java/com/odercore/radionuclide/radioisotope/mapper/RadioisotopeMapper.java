package com.odercore.radionuclide.radioisotope.mapper;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.radionuclide.radioisotope.dto.request.upsert.RadioisotopeUpsertDto;
import com.odercore.radionuclide.radioisotope.dto.response.RadioisotopeDto;
import com.odercore.radionuclide.radioisotope.entity.Radioisotope;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class RadioisotopeMapper extends AbstractMapper<
        Radioisotope,
        RadioisotopeDto,
        RadioisotopeUpsertDto> {

    @Override
    public abstract RadioisotopeDto toDto(Radioisotope entity);

    @Override
    public abstract Radioisotope toEntity(RadioisotopeUpsertDto upsertDto);
}
