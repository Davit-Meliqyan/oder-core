package com.odercore.equipment.cyclotron.target.repository;

import com.odercore.equipment.cyclotron.target.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TargetRepository extends JpaRepository<Target, UUID> {

    List<Target> findByCyclotronId(UUID cyclotronId);

}
