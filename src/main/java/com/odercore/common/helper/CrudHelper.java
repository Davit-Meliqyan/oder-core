package com.odercore.common.helper;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CrudHelper {

    public <E> E getById(
            JpaRepository<E, UUID> repository,
            UUID id
    ) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(" not found: " + id));
    }
}
