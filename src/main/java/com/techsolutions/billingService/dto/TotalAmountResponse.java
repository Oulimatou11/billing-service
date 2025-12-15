package com.techsolutions.billingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class TotalAmountResponse {

    private BigDecimal totalAmount;
}
