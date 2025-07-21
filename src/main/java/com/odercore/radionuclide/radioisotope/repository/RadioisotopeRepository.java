package com.odercore.radionuclide.radioisotope.repository;

import com.odercore.radionuclide.radioisotope.entity.Radioisotope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface RadioisotopeRepository extends JpaRepository<Radioisotope, UUID> {
}
