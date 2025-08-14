package com.odercore.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BigDecimalLimit {

    private BigDecimal min;
    private BigDecimal max;

}
