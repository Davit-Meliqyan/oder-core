package com.odercore.radionuclide.radioisotope.entity;

import com.odercore.common.entity.BaseEntity;
import com.odercore.radionuclide.enums.Aim;
import jakarta.persistence.*;
import lombok.*;

import java.util.concurrent.TimeUnit;

@Entity
@Table(name = "radioisotopes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Radioisotope extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String shortName;

    @Column(nullable = false)
    private Integer halfLife;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TimeUnit halfLifeUnite;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Aim aim;

}
