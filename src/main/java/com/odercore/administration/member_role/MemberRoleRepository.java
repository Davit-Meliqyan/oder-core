package com.odercore.administration.member_role;

import com.odercore.administration.role.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface MemberRoleRepository extends JpaRepository<MemberRole, UUID> {

    List<MemberRole> findByMemberId(UUID memberId);

    @Modifying
    void deleteByMemberId(UUID memberId);

    List<MemberRole> findByRoleId(UUID roleId);

    @Modifying
    void deleteByRoleId(UUID roleId);

    @Query(value = """
            select r.*
            from roles r
            join member_roles mr on r.id = mr.role_id
            where mr.member_id = :memberId
            """, nativeQuery = true)
    Set<Role> findRolesByMemberId(@Param("memberId") UUID memberId);

}