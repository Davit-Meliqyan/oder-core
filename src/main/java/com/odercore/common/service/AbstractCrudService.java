package com.odercore.common.service;

import com.odercore.common.dto.BaseDto;
import com.odercore.common.entity.BaseEntity;
import com.odercore.common.mapper.AbstractMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public abstract class AbstractCrudService<
        ENTITY extends BaseEntity,
        DTO extends BaseDto,
        UPSERT,
        PATCH> {

    protected final JpaRepository<ENTITY, UUID> repository;
    protected final AbstractMapper<ENTITY, DTO, UPSERT> mapper;

    protected AbstractCrudService(
            JpaRepository<ENTITY, UUID> repository,
            AbstractMapper<ENTITY, DTO, UPSERT> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public DTO getById(UUID id) {
        return mapper.toDto(findEntityOrThrow(id));
    }

    public DTO getById(UUID parentId, UUID id) {
        ENTITY entity = findEntityOrThrow(id);
        checkParent(parentId, entity);
        return mapper.toDto(entity);
    }

    public DTO create(UPSERT createDto) {
        beforeMapping(createDto);
        ENTITY entity = mapper.toEntity(createDto);
        beforeSaving(createDto, entity);
        return createEntity(entity);
    }

    public DTO create(UUID parentId, UPSERT createDto) {
        checkParent(parentId);
        beforeMapping(createDto);
        ENTITY entity = mapper.toEntity(createDto);
        beforeSaving(createDto, entity);
        beforeSaving(parentId, createDto, entity);
        return createEntity(entity);
    }

    private DTO createEntity(ENTITY entity) {
        ENTITY savedEntity = repository.save(entity);
        afterSaving(savedEntity);
        return mapper.toDto(savedEntity);
    }

    public void delete(UUID id) {
        findEntityOrThrow(id);
        repository.deleteById(id);
    }

    public void delete(UUID parentId, UUID id) {
        ENTITY entity = findEntityOrThrow(id);
        checkParent(parentId, entity);
        repository.deleteById(id);
    }

    public ENTITY findEntityOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found: " + id));
    }

    public boolean existsById(UUID id) {
        return repository.existsById(id);
    }

    protected void beforeMapping(UPSERT createDto) {
    }

    protected void beforeSaving(UPSERT createDto, ENTITY entity) {
    }

    protected void beforeSaving(UUID parentId, UPSERT createDto, ENTITY entity) {
    }

    protected void afterSaving(ENTITY entity) {
    }

    protected void checkParent(UUID parentId) {
    }

    protected void checkParent(UUID parentId, ENTITY entity) {
    }

}
