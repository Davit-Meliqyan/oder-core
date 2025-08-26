package com.odercore.administration.member.entity;

import com.odercore.common.entity.BaseEntity;
import com.odercore.common.enums.DurationUnit;
import com.odercore.common.enums.ExpiryReminder;
import com.odercore.common.utils.HasFileURLs;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "member_licenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemberLicense extends BaseEntity implements HasFileURLs {

    @Column(name = "member_id", nullable = false)
    private UUID memberId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "organization_issued")
    private String organizationIssued;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "date_of_expiry")
    private LocalDateTime dateOfExpiry;

    @Enumerated(EnumType.STRING)
    @Column(name = "expiry_reminder")
    private ExpiryReminder expiryReminder;

    @Column(name = "training_duration_value")
    private Integer trainingDurationValue;

    @Enumerated(EnumType.STRING)
    @Column(name = "training_duration_unit")
    private DurationUnit trainingDurationUnit;

    @ElementCollection
    @CollectionTable(name = "member_license_files", joinColumns = @JoinColumn(name = "license_id"))
    @Column(name = "file_url")
    private List<String> fileURLs = new ArrayList<>();

    @Column(name = "active")
    private Boolean active;
}