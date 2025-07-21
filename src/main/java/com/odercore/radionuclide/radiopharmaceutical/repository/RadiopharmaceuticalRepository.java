package com.odercore.radionuclide.radiopharmaceutical.repository;

import com.odercore.radionuclide.radiopharmaceutical.entity.Radiopharmaceutical;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RadiopharmaceuticalRepository extends JpaRepository<Radiopharmaceutical, UUID> {
}
