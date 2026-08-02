package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository
        extends JpaRepository<Stock, Long> {


    boolean existsByProductId(Long productId);


    Optional<Stock> findByProductId(Long productId);


    Optional<Stock> findByProductIdAndWarehouseId(
            Long productId,
            Long warehouseId
    );

}