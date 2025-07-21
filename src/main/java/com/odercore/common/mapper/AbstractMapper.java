package com.odercore.common.mapper;

import com.odercore.common.dto.BaseDto;
import com.odercore.common.entity.BaseEntity;

public abstract class AbstractMapper<
        ENTITY extends BaseEntity,
        DTO extends BaseDto,
        UPSERT> {

    public abstract ENTITY toEntity(UPSERT upsertDto);

    public abstract DTO toDto(ENTITY entity);
}
