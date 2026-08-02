package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.StockAdjustment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockAdjustmentRepository
        extends JpaRepository<StockAdjustment, Long> {


    boolean existsByAdjustmentNumber(
            String adjustmentNumber
    );

}