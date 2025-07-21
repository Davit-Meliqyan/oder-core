package com.odercore.subsystem.gassystem.repository;

import com.odercore.subsystem.gassystem.entity.GasSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GasSystemRepository extends JpaRepository<GasSystem, UUID> {
}
