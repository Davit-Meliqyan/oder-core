package com.odercore.equipment.hotlab.module.repository;

import com.odercore.equipment.hotlab.module.entity.HotLabModule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface HotLabModuleRepository extends JpaRepository<HotLabModule, UUID> {
    List<HotLabModule> findByHotLabId(UUID hotLabId);
}
