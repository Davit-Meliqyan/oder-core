package com.odercore.equipment.cyclotron.exitport.service;

import com.odercore.equipment.cyclotron.exitport.dto.ExitPortDto;
import com.odercore.equipment.cyclotron.exitport.entity.ExitPort;
import com.odercore.equipment.cyclotron.exitport.mapper.ExitPortMapper;
import com.odercore.equipment.cyclotron.exitport.repository.ExitPortRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExitPortService {

    private final ExitPortRepository repository;
    private final ExitPortMapper mapper;

    public List<ExitPortDto> getAllByCyclotronId(UUID cyclotronId) {
        return repository.findByCyclotronId(cyclotronId).stream()
                .map(mapper::toDto)
                .toList();
    }

    public void save(ExitPort entity) {
        repository.save(entity);
    }

}
