package com.odercore.equipment.cyclotron.target.entity;

import com.odercore.common.entity.BaseEntity;
import com.odercore.equipment.cyclotron.entity.Cyclotron;
import com.odercore.equipment.cyclotron.exitport.entity.ExitPort;
import com.odercore.equipment.cyclotron.target.enums.TargetType;
import com.odercore.radionuclide.radioisotope.entity.Radioisotope;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "targets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Target extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "target_type", nullable = false)
    private TargetType targetType;

    @Column(name = "target_pressure")
    private BigDecimal targetPressure;

    @Column(name = "target_current")
    private BigDecimal targetCurrent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cyclotron_id", nullable = false)
    private Cyclotron cyclotron;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "radioisotope_id", nullable = false)
    private Radioisotope radioisotope;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "exit_port_id", nullable = false)
    private ExitPort exitPort;
}
