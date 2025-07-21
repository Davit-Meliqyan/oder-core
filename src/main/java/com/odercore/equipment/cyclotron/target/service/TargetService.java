package com.odercore.equipment.cyclotron.target.service;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.common.service.AbstractCrudService;
import com.odercore.equipment.cyclotron.service.CyclotronService;
import com.odercore.equipment.cyclotron.target.dto.request.patch.TargetPatchDto;
import com.odercore.equipment.cyclotron.target.dto.request.upsert.TargetUpsertDto;
import com.odercore.equipment.cyclotron.target.dto.response.TargetDto;
import com.odercore.equipment.cyclotron.target.entity.Target;
import com.odercore.equipment.cyclotron.target.repository.TargetRepository;
import com.odercore.radionuclide.radioisotope.service.RadioisotopeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TargetService extends AbstractCrudService<
        Target,
        TargetDto,
        TargetUpsertDto,
        TargetPatchDto
        > {

    private final TargetRepository repository;
    private final CyclotronService cyclotronService;
    private final RadioisotopeService radioisotopeService;

    protected TargetService(
            TargetRepository repository,
            AbstractMapper<Target, TargetDto, TargetUpsertDto> mapper,
            CyclotronService cyclotronService,
            RadioisotopeService radioisotopeService) {
        super(repository, mapper);
        this.repository = repository;
        this.cyclotronService = cyclotronService;
        this.radioisotopeService = radioisotopeService;
    }

    public List<TargetDto> getAllByCyclotronId(UUID cyclotronId) {
        return repository.findByCyclotronId(cyclotronId).stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    protected void beforeSaving(UUID cyclotronId, TargetUpsertDto createDto, Target entity) {
        setCyclotron(cyclotronId, entity);
        setRadioisotope(createDto.getRadioisotopeId(), entity);
    }

    private void setCyclotron(UUID cyclotronId, Target entity) {
        if (cyclotronId == null) {
            throw new IllegalArgumentException("Cyclotron ID is required");
        }
        entity.setCyclotron(cyclotronService.findEntityOrThrow(cyclotronId));
    }

    private void setRadioisotope(UUID radioisotopeId, Target entity) {
        if (radioisotopeId == null) {
            throw new IllegalArgumentException("Radioisotope ID is required");
        }
        entity.setRadioisotope(radioisotopeService.findEntityOrThrow(radioisotopeId));
    }

    @Override
    protected void checkParent(UUID cyclotronId, Target target) {
        checkParent(cyclotronId);
        if (!cyclotronId.equals(target.getCyclotron().getId())) {
            throw new RuntimeException("Cyclotron not found: " + cyclotronId);
        }
    }

    @Override
    protected void checkParent(UUID cyclotronId) {
        if (!cyclotronService.existsById(cyclotronId)) {
            throw new RuntimeException("Cyclotron not found: " + cyclotronId);
        }
    }

}
