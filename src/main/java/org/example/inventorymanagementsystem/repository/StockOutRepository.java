package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.StockOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockOutRepository extends JpaRepository<StockOut, Long> {

    boolean existsByReferenceNo(String referenceNo);

}