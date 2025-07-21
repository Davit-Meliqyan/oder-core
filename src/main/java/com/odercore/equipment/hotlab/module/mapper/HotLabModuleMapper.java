package com.odercore.equipment.hotlab.module.mapper;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.equipment.hotlab.module.dto.request.upsert.HotLabModuleUpsertDto;
import com.odercore.equipment.hotlab.module.dto.response.HotLabModuleDto;
import com.odercore.equipment.hotlab.module.entity.HotLabModule;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class HotLabModuleMapper extends AbstractMapper<
        HotLabModule,
        HotLabModuleDto,
        HotLabModuleUpsertDto> {

    @Override
    @Mapping(source = "hotLab.id", target = "hotLabId")
    public abstract HotLabModuleDto toDto(HotLabModule entity);

    @Override
    @Mapping(target = "hotLab", ignore = true)
    public abstract HotLabModule toEntity(HotLabModuleUpsertDto upsertDto);

}
