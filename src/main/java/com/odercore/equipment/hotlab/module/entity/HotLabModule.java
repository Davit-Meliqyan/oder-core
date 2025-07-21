package com.odercore.equipment.hotlab.module.entity;

import com.odercore.equipment.entity.Equipment;
import com.odercore.equipment.hotlab.entity.HotLab;
import com.odercore.equipment.hotlab.module.enums.ModuleType;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "hot_lab_modules")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HotLabModule extends Equipment {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hot_lab_id", nullable = false)
    private HotLab hotLab;


    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(
            name = "hot_lab_module_types",
            joinColumns = @JoinColumn(name = "module_id")
    )
    private Set<ModuleType> moduleTypes;

}
