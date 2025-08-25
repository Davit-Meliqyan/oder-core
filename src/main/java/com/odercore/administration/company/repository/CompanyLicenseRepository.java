package com.odercore.administration.company.repository;

import com.odercore.administration.company.entity.CompanyLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyLicenseRepository extends JpaRepository<CompanyLicense, UUID> {
}
