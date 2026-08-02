package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.SalesOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesOrderRepository
        extends JpaRepository<SalesOrder, Long> {

    boolean existsBySoNumber(String soNumber);

}