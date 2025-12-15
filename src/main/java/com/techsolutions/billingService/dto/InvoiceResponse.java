package com.techsolutions.billingService.dto;

import com.techsolutions.billingService.domain.Invoice;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
public class InvoiceResponse {
    private Long id;
    private Long clientId;
    private BigDecimal amount;
    private String description;
    private LocalDate dateEmission;
    private LocalDate datePaiement;
    private String status;
    private String paymentMethod;

    public InvoiceResponse(Invoice invoice) {
        this.id = invoice.getId();
        this.clientId = invoice.getClientId();
        this.amount = invoice.getAmount();
        this.description = invoice.getDescription();
        this.dateEmission = invoice.getDateEmission();
        this.datePaiement = invoice.getDatePaiement();
        this.status = invoice.getStatus();
    }

    public InvoiceResponse(Long id, Long clientId, BigDecimal amount, String description, LocalDate dateEmission, LocalDate datePaiement, String status, String s) {
        this.id = id;
        this.clientId = clientId;
        this.amount = amount;
        this.description = description;
        this.dateEmission = dateEmission;
        this.datePaiement = datePaiement;
        this.status = status;
        this.paymentMethod = s;
    }
}
