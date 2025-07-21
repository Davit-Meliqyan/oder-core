package com.odercore.equipment.cyclotron.target.mapper;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.equipment.cyclotron.target.dto.request.upsert.TargetUpsertDto;
import com.odercore.equipment.cyclotron.target.dto.response.TargetDto;
import com.odercore.equipment.cyclotron.target.entity.Target;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class TargetMapper extends AbstractMapper<
        Target,
        TargetDto,
        TargetUpsertDto> {

    @Override
    @Mapping(source = "cyclotron.id", target = "cyclotronId")
    @Mapping(source = "radioisotope.id", target = "radioisotopeId")
    public abstract TargetDto toDto(Target entity);

    @Override
    @Mapping(target = "cyclotron", ignore = true)
    @Mapping(target = "radioisotope", ignore = true)
    public abstract Target toEntity(TargetUpsertDto upsertDto);

}
