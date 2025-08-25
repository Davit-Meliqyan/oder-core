package com.odercore.common.service;

import com.odercore.common.dto.BaseDto;
import com.odercore.common.entity.BaseEntity;
import com.odercore.common.mapper.AbstractMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<DTO> getAll() {
        return findAll().stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public DTO getById(UUID parentId, UUID id) {
        ENTITY entity = findEntityOrThrow(id);
        checkParent(parentId, entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public DTO create(UPSERT createDto) {
        beforeMapping(createDto);
        ENTITY entity = mapper.toEntity(createDto);
        beforeSaving(createDto, entity);
        return createEntity(createDto, entity);
    }

    @Transactional
    public DTO create(UUID parentId, UPSERT createDto) {
        checkParent(parentId);
        beforeMapping(createDto);
        ENTITY entity = mapper.toEntity(createDto);
        beforeSaving(createDto, entity);
        beforeSaving(parentId, createDto, entity);
        return createEntity(createDto, entity);
    }

    @Transactional
    protected DTO createEntity(UPSERT createDto, ENTITY entity) {
        ENTITY savedEntity = repository.save(entity);
        afterSaving(createDto, savedEntity);
        return mapper.toDto(savedEntity);
    }

    @Transactional
    public DTO update(UUID id, UPSERT updateDto) {
        ENTITY entity = findEntityOrThrow(id);
        beforeUpdate(updateDto, entity);
        mapUpdateToEntity(updateDto, entity);
        ENTITY saved = repository.save(entity);
        afterSaving(updateDto, saved);
        return mapper.toDto(saved);
    }

    @Transactional
    public void delete(UUID id) {
        findEntityOrThrow(id);
        repository.deleteById(id);
    }

    @Transactional
    public void delete(UUID parentId, UUID id) {
        ENTITY entity = findEntityOrThrow(id);
        checkParent(parentId, entity);
        repository.deleteById(id);
    }

    public ENTITY findEntityOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Entity not found: " + id));
    }

    public List<ENTITY> findAll() {
        return repository.findAll();
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

    protected void afterSaving(UPSERT createDto, ENTITY entity) {
    }

    protected void checkParent(UUID parentId) {
    }

    protected void checkParent(UUID parentId, ENTITY entity) {
    }

    protected void beforeUpdate(UPSERT createDto, ENTITY entity) {
    }

    protected void mapUpdateToEntity(UPSERT updateDto, ENTITY entity) {
        ENTITY updatedEntity = mapper.toEntity(updateDto);
        copyNonNullProperties(updatedEntity, entity);
    }

    protected void copyNonNullProperties(ENTITY source, ENTITY target) {
    }

}
