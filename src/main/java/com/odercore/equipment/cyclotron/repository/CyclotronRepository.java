package com.odercore.equipment.cyclotron.repository;

import com.odercore.equipment.cyclotron.entity.Cyclotron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CyclotronRepository extends JpaRepository<Cyclotron, UUID> {
}
