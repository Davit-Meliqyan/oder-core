package com.odercore.radionuclide.radiopharmaceutical.controller;

import com.odercore.common.controller.AbstractCrudController;
import com.odercore.common.service.AbstractCrudService;
import com.odercore.radionuclide.radiopharmaceutical.dto.request.patch.RadiopharmaceuticalPatchDto;
import com.odercore.radionuclide.radiopharmaceutical.dto.request.upsert.RadiopharmaceuticalUpsertDto;
import com.odercore.radionuclide.radiopharmaceutical.dto.response.RadiopharmaceuticalDto;
import com.odercore.radionuclide.radiopharmaceutical.entity.Radiopharmaceutical;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/radiopharmaceuticals")
@Tag(name = "Radiopharmaceuticals", description = "Radiopharmaceuticals Management")
public class RadiopharmaceuticalController extends AbstractCrudController<
        Radiopharmaceutical,
        RadiopharmaceuticalDto,
        RadiopharmaceuticalUpsertDto,
        RadiopharmaceuticalPatchDto
        > {

    protected RadiopharmaceuticalController(
            AbstractCrudService<
                    Radiopharmaceutical,
                    RadiopharmaceuticalDto,
                    RadiopharmaceuticalUpsertDto,
                    RadiopharmaceuticalPatchDto> service) {
        super(service);
    }
}
