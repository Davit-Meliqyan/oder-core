package com.odercore.radionuclide.radioisotope.service;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.common.service.AbstractCrudService;
import com.odercore.radionuclide.radioisotope.dto.request.patch.RadioisotopePatchDto;
import com.odercore.radionuclide.radioisotope.dto.request.upsert.RadioisotopeUpsertDto;
import com.odercore.radionuclide.radioisotope.dto.response.RadioisotopeDto;
import com.odercore.radionuclide.radioisotope.entity.Radioisotope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RadioisotopeService extends AbstractCrudService<
        Radioisotope,
        RadioisotopeDto,
        RadioisotopeUpsertDto,
        RadioisotopePatchDto
        > {

    protected RadioisotopeService(JpaRepository<Radioisotope, UUID> repository,
                                  AbstractMapper<Radioisotope, RadioisotopeDto, RadioisotopeUpsertDto> mapper) {
        super(repository, mapper);
    }
}
