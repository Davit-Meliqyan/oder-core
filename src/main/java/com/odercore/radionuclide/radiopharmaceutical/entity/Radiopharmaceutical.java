package com.odercore.radionuclide.radiopharmaceutical.entity;

import com.odercore.common.entity.BaseEntity;
import com.odercore.radionuclide.enums.Aim;
import com.odercore.radionuclide.radioisotope.entity.Radioisotope;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "radiopharmaceuticals")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Radiopharmaceutical extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String shortName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Aim aim;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "radioisotope_id", nullable = false)
    private Radioisotope radioisotope;

}
