package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceItemRepository
        extends JpaRepository<InvoiceItem, Long> {

}