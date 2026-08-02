package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.PurchaseReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseReturnItemRepository
        extends JpaRepository<PurchaseReturnItem, Long> {

}