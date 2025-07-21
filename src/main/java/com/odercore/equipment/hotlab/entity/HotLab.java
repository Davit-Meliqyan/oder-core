package com.odercore.equipment.hotlab.entity;

import com.odercore.equipment.entity.Equipment;
import com.odercore.equipment.hotlab.enums.CabinetType;
import com.odercore.equipment.hotlab.enums.CleanClassification;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "hot_labs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotLab extends Equipment {

    @Enumerated(EnumType.STRING)
    @Column(name = "clean_classification", nullable = false)
    private CleanClassification cleanClassification;

    @Enumerated(EnumType.STRING)
    @Column(name = "cabinet_type", nullable = false)
    private CabinetType cabinetType;

}
