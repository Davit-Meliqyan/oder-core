package com.odercore.administration.company.entity;

import com.odercore.common.entity.BaseEntity;
import com.odercore.administration.company.enums.ExpiryReminder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company_licenses")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyLicense extends BaseEntity {

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

    @ElementCollection
    @CollectionTable(name = "company_license_files", joinColumns = @JoinColumn(name = "license_id"))
    @Column(name = "file_url")
    private List<String> fileURLs = new ArrayList<>();

    @Column(name = "active")
    private Boolean active;
}
