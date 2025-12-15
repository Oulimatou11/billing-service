package com.techsolutions.billingService.web;

import com.techsolutions.billingService.domain.Invoice;
import com.techsolutions.billingService.dto.CreateInvoiceRequest;
import com.techsolutions.billingService.dto.InvoiceResponse;
import com.techsolutions.billingService.dto.InvoiceStatusResponse;
import com.techsolutions.billingService.dto.TotalAmountResponse;
import com.techsolutions.billingService.service.InvoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name="Billing API",description = "API pour la gestion de la facturation des clients de Tech Solutions")
public class BillingController {

    @Autowired
    InvoiceService invoiceService;

    @PostMapping("/invoices")
    public InvoiceResponse createInvoice(@RequestBody CreateInvoiceRequest request){
         Invoice invoice = invoiceService.createInvoice(request);
          return new InvoiceResponse(invoice);
    }

    @GetMapping("/invoices/{id}")
    public InvoiceResponse getInvoice(@PathVariable Long id) {
        Invoice invoice = invoiceService.getInvoiceById(id);
        return new InvoiceResponse(invoice);
    }

    @GetMapping("/clients/{clientId}/invoices")
    public List<InvoiceResponse> getClientInvoices(@PathVariable Long clientId) {

        List<Invoice> invoices = invoiceService.getClientInvoices(clientId);

        return invoices.stream()
                .map(invoice -> new InvoiceResponse(invoice))
                .collect(Collectors.toList());
    }

    @PutMapping("/invoices/{id}/pay")
    public InvoiceStatusResponse payInvoice(@PathVariable Long id) {
        Invoice invoice = invoiceService.payInvoice(id);
        return new InvoiceStatusResponse(invoice.getStatus());
    }

    @GetMapping("/clients/{clientId}/total")
    public TotalAmountResponse getClientTotal(@PathVariable Long clientId) {
        BigDecimal total = invoiceService.getClientTotal(clientId);
        return new TotalAmountResponse(total);
    }

    @GetMapping("/invoices/pending")
    public List<InvoiceResponse> getPendingInvoices(
            @RequestParam(defaultValue = "15") int days) {

        List<Invoice> invoices = invoiceService.getPendingInvoices(days);

        return invoices.stream()
                .map(invoice -> new InvoiceResponse(invoice))
                .toList();
    }
}
