package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.StockTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTransferRepository
        extends JpaRepository<StockTransfer, Long> {


    boolean existsByTransferNumber(String transferNumber);

}