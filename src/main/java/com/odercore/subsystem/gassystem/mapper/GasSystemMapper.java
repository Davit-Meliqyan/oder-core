package com.odercore.subsystem.gassystem.mapper;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.subsystem.gassystem.dto.request.upsert.GasSystemUpsertDto;
import com.odercore.subsystem.gassystem.dto.response.GasSystemDto;
import com.odercore.subsystem.gassystem.entity.GasSystem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class GasSystemMapper extends AbstractMapper<
        GasSystem,
        GasSystemDto,
        GasSystemUpsertDto> {

    @Override
    public abstract GasSystemDto toDto(GasSystem entity);

    @Override
    public abstract GasSystem toEntity(GasSystemUpsertDto upsertDto);
}
