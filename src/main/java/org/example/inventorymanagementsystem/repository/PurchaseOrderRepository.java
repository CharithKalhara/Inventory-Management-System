package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrder, Long> {

    boolean existsByPoNumber(String poNumber);

}