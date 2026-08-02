package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceRepository
        extends JpaRepository<Invoice, Long> {


    boolean existsByInvoiceNumber(String invoiceNumber);


    List<Invoice> findTop5ByOrderByInvoiceDateDesc();

}