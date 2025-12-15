package com.techsolutions.billingService.service;

import com.techsolutions.billingService.domain.Invoice;
import com.techsolutions.billingService.dto.CreateInvoiceRequest;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceService {
    Invoice createInvoice(CreateInvoiceRequest request);
    Invoice getInvoiceById(Long id);
    List<Invoice> getClientInvoices(Long clientId);
    Invoice payInvoice(Long id);
    BigDecimal getClientTotal(Long clientId);
    List<Invoice> getPendingInvoices(int daysThreshold);
}
