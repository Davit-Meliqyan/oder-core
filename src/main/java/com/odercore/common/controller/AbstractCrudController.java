package com.odercore.common.controller;

import com.odercore.common.dto.BaseDto;
import com.odercore.common.entity.BaseEntity;
import com.odercore.common.service.AbstractCrudService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

public abstract class AbstractCrudController<

        ENTITY extends BaseEntity,
        DTO extends BaseDto,
        UPSERT,
        PATCH> {

    protected final AbstractCrudService<ENTITY, DTO, UPSERT, PATCH> service;

    protected AbstractCrudController(AbstractCrudService<ENTITY, DTO, UPSERT, PATCH> service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DTO> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping
    public ResponseEntity<List<DTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @PostMapping
    public ResponseEntity<DTO> create(@RequestBody UPSERT dto) {
        return ResponseEntity.ok(service.create(dto));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<DTO> update(@PathVariable UUID id, @RequestBody UPSERT dto) {
//        return ResponseEntity.ok(service.update(id, dto));
//    }
//
//    @PatchMapping("/{id}")
//    public ResponseEntity<DTO> patch(@PathVariable UUID id, @RequestBody P dto) {
//        return ResponseEntity.ok(service.patch(id, dto));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}
