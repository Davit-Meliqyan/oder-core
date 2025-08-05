package com.odercore.equipment.cyclotron.exitport.dto;

import com.odercore.common.dto.BaseDto;
import lombok.*;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ExitPortDto extends BaseDto {

    private Integer portNumber;
    private UUID cyclotronId;

}
