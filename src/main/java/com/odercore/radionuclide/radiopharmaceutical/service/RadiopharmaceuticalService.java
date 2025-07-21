package com.odercore.radionuclide.radiopharmaceutical.service;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.common.service.AbstractCrudService;
import com.odercore.radionuclide.radioisotope.entity.Radioisotope;
import com.odercore.radionuclide.radioisotope.repository.RadioisotopeRepository;
import com.odercore.radionuclide.radiopharmaceutical.dto.request.patch.RadiopharmaceuticalPatchDto;
import com.odercore.radionuclide.radiopharmaceutical.dto.request.upsert.RadiopharmaceuticalUpsertDto;
import com.odercore.radionuclide.radiopharmaceutical.dto.response.RadiopharmaceuticalDto;
import com.odercore.radionuclide.radiopharmaceutical.entity.Radiopharmaceutical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RadiopharmaceuticalService extends AbstractCrudService<
        Radiopharmaceutical,
        RadiopharmaceuticalDto,
        RadiopharmaceuticalUpsertDto,
        RadiopharmaceuticalPatchDto
        > {

    private final RadioisotopeRepository radioisotopeRepository;

    protected RadiopharmaceuticalService(JpaRepository<Radiopharmaceutical, UUID> repository,
                                         AbstractMapper<Radiopharmaceutical, RadiopharmaceuticalDto, RadiopharmaceuticalUpsertDto> mapper, RadioisotopeRepository radioisotopeRepository) {
        super(repository, mapper);
        this.radioisotopeRepository = radioisotopeRepository;
    }

    @Override
    protected void beforeSaving(RadiopharmaceuticalUpsertDto createDto, Radiopharmaceutical entity) {
        UUID isotopeId = createDto.getRadioisotopeId();
        if (isotopeId == null) {
            throw new IllegalArgumentException("Radioisotope ID is required");
        }

        Radioisotope isotope = radioisotopeRepository.findById(isotopeId)
                .orElseThrow(() -> new RuntimeException("Radioisotope not found: " + isotopeId));

        entity.setRadioisotope(isotope);
    }

}
