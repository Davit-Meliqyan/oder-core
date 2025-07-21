package com.odercore.equipment.cyclotron.exitport.mapper;

import com.odercore.equipment.cyclotron.exitport.dto.ExitPortDto;
import com.odercore.equipment.cyclotron.exitport.entity.ExitPort;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class ExitPortMapper {

    @Mapping(source = "cyclotron.id", target = "cyclotronId")
    public abstract ExitPortDto toDto(ExitPort entity);

}
