package com.odercore.equipment.cyclotron.exitport.repository;

import com.odercore.equipment.cyclotron.exitport.entity.ExitPort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ExitPortRepository extends JpaRepository<ExitPort, UUID> {

    List<ExitPort> findByCyclotronId(UUID cyclotronId);

    void deleteByCyclotronId(UUID cyclotronId);

}
