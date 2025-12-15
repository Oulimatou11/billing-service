package com.techsolutions.billingService.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateInvoiceRequest {

    public Long clientId;
    public BigDecimal amount;
    public String description;
}
