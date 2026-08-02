package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.StockIn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockInRepository extends JpaRepository<StockIn, Long> {

    boolean existsByReferenceNo(String referenceNo);
}