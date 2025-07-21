package com.odercore.equipment.hotlab.mapper;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.equipment.hotlab.dto.request.upsert.HotLabUpsertDto;
import com.odercore.equipment.hotlab.dto.response.HotLabDto;
import com.odercore.equipment.hotlab.entity.HotLab;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public abstract class HotLabMapper extends AbstractMapper<
        HotLab,
        HotLabDto,
        HotLabUpsertDto> {

    @Override
    public abstract HotLabDto toDto(HotLab entity);

    @Override
    public abstract HotLab toEntity(HotLabUpsertDto upsertDto);
}
