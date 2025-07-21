package com.odercore.equipment.cyclotron.service;

import com.odercore.common.mapper.AbstractMapper;
import com.odercore.common.service.AbstractCrudService;
import com.odercore.equipment.cyclotron.dto.request.patch.CyclotronPatchDto;
import com.odercore.equipment.cyclotron.dto.request.upsert.CyclotronUpsertDto;
import com.odercore.equipment.cyclotron.dto.response.CyclotronDto;
import com.odercore.equipment.cyclotron.entity.Cyclotron;
import com.odercore.equipment.cyclotron.exitport.entity.ExitPort;
import com.odercore.equipment.cyclotron.exitport.service.ExitPortService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.stream.IntStream;

@Service
public class CyclotronService extends AbstractCrudService<
        Cyclotron,
        CyclotronDto,
        CyclotronUpsertDto,
        CyclotronPatchDto
        > {

    private final ExitPortService exitPortService;

    protected CyclotronService(JpaRepository<Cyclotron, UUID> repository,
                               AbstractMapper<Cyclotron, CyclotronDto, CyclotronUpsertDto> mapper, ExitPortService exitPortService) {
        super(repository, mapper);
        this.exitPortService = exitPortService;
    }

    @Override
    protected void afterSaving(Cyclotron cyclotron) {
        createExitPorts(cyclotron);
    }

    private void createExitPorts(Cyclotron cyclotron) {
        IntStream.rangeClosed(1, cyclotron.getExitPortsCount())
                .mapToObj(i -> ExitPort.builder()
                        .portNumber(i)
                        .cyclotron(cyclotron)
                        .build())
                .forEach(exitPortService::save);
    }

}
