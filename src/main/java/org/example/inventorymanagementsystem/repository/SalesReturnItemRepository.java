package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.SalesReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesReturnItemRepository
        extends JpaRepository<SalesReturnItem, Long> {

}