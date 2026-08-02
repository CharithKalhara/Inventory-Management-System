package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.PurchaseReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseReturnRepository
        extends JpaRepository<PurchaseReturn, Long> {

    boolean existsByReturnNumber(String returnNumber);

}