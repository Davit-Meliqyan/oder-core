package com.odercore.administration.member.repository;

import com.odercore.administration.member.entity.MemberLicense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberLicenseRepository extends JpaRepository<MemberLicense, UUID> {
    List<MemberLicense> findAllByMemberId(UUID memberId);

    Optional<MemberLicense> findByIdAndMemberId(UUID id, UUID memberId);

    void deleteByIdAndMemberId(UUID id, UUID memberId);

}