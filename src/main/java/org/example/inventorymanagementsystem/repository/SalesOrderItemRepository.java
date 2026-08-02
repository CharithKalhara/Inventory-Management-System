package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.SalesOrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderItemRepository
        extends JpaRepository<SalesOrderItem, Long> {

}