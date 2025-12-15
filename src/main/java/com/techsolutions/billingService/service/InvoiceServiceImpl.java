package com.techsolutions.billingService.service;


import com.techsolutions.billingService.domain.Invoice;
import com.techsolutions.billingService.domain.PaymentMethod;
import com.techsolutions.billingService.dto.CreateInvoiceRequest;
import com.techsolutions.billingService.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class InvoiceServiceImpl implements InvoiceService {
    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Invoice createInvoice(CreateInvoiceRequest request) {

        Invoice invoice = new Invoice();
        invoice.setClientId(request.getClientId());
        invoice.setAmount(request.getAmount());
        invoice.setDescription(request.getDescription());
        invoice.setDateEmission(LocalDate.now());
        invoice.setStatus("PENDING");

        return invoiceRepository.save(invoice);
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public List<Invoice> getClientInvoices(Long clientId) {
        return invoiceRepository.findByClientId(clientId);
    }

    @Override
    public Invoice payInvoice(Long id) {
        Invoice invoice = invoiceRepository.findById(id).orElse(null);

        if ("PAID".equals(invoice.getStatus())) {
            throw new IllegalArgumentException("Facture déja payée");
        }
        invoice.setStatus("PAID");
        invoice.setDatePaiement(java.time.LocalDate.now());
        invoice.setPaymentMethod(PaymentMethod.CASH);

        return invoiceRepository.save(invoice);
    }

    @Override
    public BigDecimal getClientTotal(Long clientId) {
        BigDecimal total = invoiceRepository.calculateTotalByClientId(clientId);
          return total != null ? total : BigDecimal.ZERO;
    }

    @Override
    public List<Invoice> getPendingInvoices(int daysThreshold) {
        LocalDate dateLimit = LocalDate.now().minusDays(daysThreshold);
        return invoiceRepository.findPendingInvoicesOlderThan(dateLimit);
    }
}
