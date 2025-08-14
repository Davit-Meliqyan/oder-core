package com.odercore.company.repository;

import com.odercore.company.entity.CompanyLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyLicenseRepository extends JpaRepository<CompanyLicense, UUID> {
}
