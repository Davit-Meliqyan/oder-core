package com.odercore.administration.member_role;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "member_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberRole {
    @Id
    @GeneratedValue
    @Column(updatable = false, nullable = false)
    private UUID id;

    @Column(name = "member_id", nullable = false)
    private UUID memberId;

    @Column(name = "role_id", nullable = false)
    private UUID roleId;
}