package com.odercore.administration.member.repository;

import com.odercore.administration.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface MembersRepository extends JpaRepository<Member, UUID> {
}
