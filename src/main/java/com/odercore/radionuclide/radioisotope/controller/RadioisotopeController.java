package com.odercore.radionuclide.radioisotope.controller;

import com.odercore.common.controller.AbstractCrudController;
import com.odercore.common.service.AbstractCrudService;
import com.odercore.radionuclide.radioisotope.dto.request.patch.RadioisotopePatchDto;
import com.odercore.radionuclide.radioisotope.dto.request.upsert.RadioisotopeUpsertDto;
import com.odercore.radionuclide.radioisotope.dto.response.RadioisotopeDto;
import com.odercore.radionuclide.radioisotope.entity.Radioisotope;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/radioisotopes")
@Tag(name = "Radioisotopes", description = "Radioisotopes Management")
public class RadioisotopeController extends AbstractCrudController<
        Radioisotope,
        RadioisotopeDto,
        RadioisotopeUpsertDto,
        RadioisotopePatchDto
        > {

    protected RadioisotopeController(
            AbstractCrudService<Radioisotope, RadioisotopeDto, RadioisotopeUpsertDto, RadioisotopePatchDto> service) {
        super(service);
    }
}
