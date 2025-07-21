package com.odercore.equipment.cyclotron.exitport.entity;

import com.odercore.common.entity.BaseEntity;
import com.odercore.equipment.cyclotron.entity.Cyclotron;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "exit_ports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExitPort extends BaseEntity {

    @Column(name = "port_number")
    private Integer portNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cyclotron_id", nullable = false)
    private Cyclotron cyclotron;

}
