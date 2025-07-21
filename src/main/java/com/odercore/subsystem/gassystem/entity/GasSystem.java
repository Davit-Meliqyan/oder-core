package com.odercore.subsystem.gassystem.entity;

import com.odercore.common.entity.BaseEntity;
import com.odercore.equipment.enums.EquipmentType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "gas_systems")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GasSystem extends BaseEntity {

    @Column(nullable = false)
    private String gasName;

    @Column(precision = 2, scale = 5)
    private BigDecimal purity;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "gas_systems_used_for",
            joinColumns = @JoinColumn(name = "gas_system_id")
    )
    @Column(name = "equipment_type")
    private Set<EquipmentType> usedFor;

}
