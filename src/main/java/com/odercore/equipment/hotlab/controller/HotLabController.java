package com.odercore.equipment.hotlab.controller;

import com.odercore.common.controller.AbstractCrudController;
import com.odercore.common.service.AbstractCrudService;
import com.odercore.equipment.hotlab.dto.request.patch.HotLabPatchDto;
import com.odercore.equipment.hotlab.dto.request.upsert.HotLabUpsertDto;
import com.odercore.equipment.hotlab.dto.response.HotLabDto;
import com.odercore.equipment.hotlab.entity.HotLab;
import com.odercore.equipment.hotlab.module.dto.request.upsert.HotLabModuleUpsertDto;
import com.odercore.equipment.hotlab.module.dto.response.HotLabModuleDto;
import com.odercore.equipment.hotlab.module.service.HotLabModuleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/hot-labs")
@Tag(name = "HotLabs", description = "Hot Labs Management")
public class HotLabController extends AbstractCrudController<
        HotLab,
        HotLabDto,
        HotLabUpsertDto,
        HotLabPatchDto
        > {

    private final HotLabModuleService moduleService;

    protected HotLabController(
            AbstractCrudService<HotLab, HotLabDto, HotLabUpsertDto, HotLabPatchDto> service,
            HotLabModuleService moduleService) {
        super(service);
        this.moduleService = moduleService;
    }

    @GetMapping("/{hotLabId}/modules/")
    public ResponseEntity<List<HotLabModuleDto>> getModules(@PathVariable UUID hotLabId) {
        return ResponseEntity.ok(moduleService.getModulesByHotLabID(hotLabId));
    }

    @GetMapping("/{hotLabId}/modules/{moduleId}/")
    public ResponseEntity<HotLabModuleDto> getModuleById(
            @PathVariable UUID hotLabId, @PathVariable UUID moduleId) {
        return ResponseEntity.ok(moduleService.getById(hotLabId, moduleId));
    }

    @PostMapping("/{hotLabId}/modules/")
    public ResponseEntity<HotLabModuleDto> createModule(
            @PathVariable UUID hotLabId, @RequestBody HotLabModuleUpsertDto upsertDto) {
        return ResponseEntity.ok(moduleService.create(hotLabId, upsertDto));
    }

    @DeleteMapping("/{hotLabId}/modules/{moduleId}/")
    public ResponseEntity<Void> deleteModule(
            @PathVariable UUID hotLabId, @PathVariable UUID moduleId) {
        moduleService.delete(hotLabId, moduleId);
        return ResponseEntity.noContent().build();
    }

}
