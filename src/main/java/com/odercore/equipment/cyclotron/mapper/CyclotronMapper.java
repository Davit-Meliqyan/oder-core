package com.odercore.equipment.cyclotron.mapper;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.equipment.cyclotron.dto.request.upsert.CyclotronUpsertDto;
import com.odercore.equipment.cyclotron.dto.response.CyclotronDto;
import com.odercore.equipment.cyclotron.entity.Cyclotron;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class CyclotronMapper extends AbstractMapper<
        Cyclotron,
        CyclotronDto,
        CyclotronUpsertDto> {

    @Override
    public abstract CyclotronDto toDto(Cyclotron entity);

    @Override
    public abstract Cyclotron toEntity(CyclotronUpsertDto upsertDto);
}
