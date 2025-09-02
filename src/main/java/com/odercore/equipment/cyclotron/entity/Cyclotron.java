package com.odercore.equipment.cyclotron.entity;

import com.odercore.equipment.entity.Equipment;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "cyclotrons")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cyclotron extends Equipment {

    @Column(name = "name")
    private String name;

    @Column(name = "beam_energy")
    private BigDecimal beamEnergy;

    @Column(name = "sources_count")
    private Integer sourcesCount;

    @Column(name = "exit_ports_count")
    private Integer exitPortsCount;

}
