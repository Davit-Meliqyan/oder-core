package com.odercore.equipment.hotlab.service;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.common.service.AbstractCrudService;
import com.odercore.equipment.hotlab.dto.request.patch.HotLabPatchDto;
import com.odercore.equipment.hotlab.dto.request.upsert.HotLabUpsertDto;
import com.odercore.equipment.hotlab.dto.response.HotLabDto;
import com.odercore.equipment.hotlab.entity.HotLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HotLabService extends AbstractCrudService<
        HotLab,
        HotLabDto,
        HotLabUpsertDto,
        HotLabPatchDto
        > {

    protected HotLabService(JpaRepository<HotLab, UUID> repository,
                            AbstractMapper<HotLab, HotLabDto, HotLabUpsertDto> mapper) {
        super(repository, mapper);
    }
}
