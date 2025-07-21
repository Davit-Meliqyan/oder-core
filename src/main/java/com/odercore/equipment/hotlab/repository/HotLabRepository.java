package com.odercore.equipment.hotlab.repository;

import com.odercore.equipment.hotlab.entity.HotLab;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface HotLabRepository extends JpaRepository<HotLab, UUID> {
}
