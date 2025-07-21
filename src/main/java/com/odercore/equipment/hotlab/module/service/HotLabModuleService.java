package com.odercore.equipment.hotlab.module.service;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.common.service.AbstractCrudService;
import com.odercore.equipment.hotlab.module.dto.request.patch.HotLabModulePatchDto;
import com.odercore.equipment.hotlab.module.dto.request.upsert.HotLabModuleUpsertDto;
import com.odercore.equipment.hotlab.module.dto.response.HotLabModuleDto;
import com.odercore.equipment.hotlab.module.entity.HotLabModule;
import com.odercore.equipment.hotlab.module.repository.HotLabModuleRepository;
import com.odercore.equipment.hotlab.service.HotLabService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class HotLabModuleService extends AbstractCrudService<
        HotLabModule,
        HotLabModuleDto,
        HotLabModuleUpsertDto,
        HotLabModulePatchDto
        > {

    private final HotLabModuleRepository repository;
    private final HotLabService hotLabService;

    protected HotLabModuleService(
            HotLabModuleRepository repository,
            AbstractMapper<HotLabModule, HotLabModuleDto, HotLabModuleUpsertDto> mapper,
            HotLabService hotLabService) {
        super(repository, mapper);
        this.repository = repository;
        this.hotLabService = hotLabService;
    }

    public List<HotLabModuleDto> getModulesByHotLabID(UUID hotLabId) {
        List<HotLabModule> modules = repository.findByHotLabId(hotLabId);
        return modules.stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    protected void beforeSaving(UUID hotLabId, HotLabModuleUpsertDto createDto, HotLabModule entity) {
        if (hotLabId == null) {
            throw new IllegalArgumentException("Hot Lab ID is required");
        }
        entity.setHotLab(hotLabService.findEntityOrThrow(hotLabId));
    }

    @Override
    protected void checkParent(UUID hotLabId, HotLabModule hotLabModule) {
        checkParent(hotLabId);
        if (!hotLabId.equals(hotLabModule.getHotLab().getId())) {
            throw new RuntimeException("Hot Lab not found: " + hotLabId);
        }
    }

    @Override
    protected void checkParent(UUID hotLabId) {
        if (!hotLabService.existsById(hotLabId)) {
            throw new RuntimeException("Hot Lab not found: " + hotLabId);
        }
    }

}
