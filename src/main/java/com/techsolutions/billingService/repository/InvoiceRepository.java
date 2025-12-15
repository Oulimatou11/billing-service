package com.techsolutions.billingService.repository;


import com.techsolutions.billingService.domain.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice,Long> {

    List<Invoice> findByClientId(Long clientId);

    List<Invoice> findByStatus(String status);

    @Query("SELECT SUM(i.amount) FROM Invoice i WHERE i.clientId = :clientId")
    BigDecimal calculateTotalByClientId(@Param("clientId") Long clientId);

    @Query("SELECT i FROM Invoice i WHERE i.status = 'PENDING' AND i.dateEmission <= :dateLimit")
    List<Invoice> findPendingInvoicesOlderThan(LocalDate dateLimit);
}
