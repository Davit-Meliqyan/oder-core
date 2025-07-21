package com.odercore.subsystem.gassystem.controller;

import com.odercore.common.controller.AbstractCrudController;
import com.odercore.common.service.AbstractCrudService;
import com.odercore.subsystem.gassystem.dto.request.patch.GasSystemPatchDto;
import com.odercore.subsystem.gassystem.dto.request.upsert.GasSystemUpsertDto;
import com.odercore.subsystem.gassystem.dto.response.GasSystemDto;
import com.odercore.subsystem.gassystem.entity.GasSystem;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/gas-systems")
@Tag(name = "Gas Systems", description = "Gas Systems Management")
public class GasSystemController extends AbstractCrudController<
        GasSystem,
        GasSystemDto,
        GasSystemUpsertDto,
        GasSystemPatchDto
        > {

    protected GasSystemController(
            AbstractCrudService<GasSystem, GasSystemDto, GasSystemUpsertDto, GasSystemPatchDto> service) {
        super(service);
    }
}
