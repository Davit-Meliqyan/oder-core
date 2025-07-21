package com.odercore.subsystem.gassystem.service;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.common.service.AbstractCrudService;
import com.odercore.subsystem.gassystem.dto.request.patch.GasSystemPatchDto;
import com.odercore.subsystem.gassystem.dto.request.upsert.GasSystemUpsertDto;
import com.odercore.subsystem.gassystem.dto.response.GasSystemDto;
import com.odercore.subsystem.gassystem.entity.GasSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GasSystemService extends AbstractCrudService<
        GasSystem,
        GasSystemDto,
        GasSystemUpsertDto,
        GasSystemPatchDto
        > {

    protected GasSystemService(JpaRepository<GasSystem, UUID> repository,
                               AbstractMapper<GasSystem, GasSystemDto, GasSystemUpsertDto> mapper) {
        super(repository, mapper);
    }
}
