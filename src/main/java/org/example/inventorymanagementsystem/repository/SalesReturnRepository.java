package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.SalesReturn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalesReturnRepository
        extends JpaRepository<SalesReturn, Long> {

    boolean existsByReturnNumber(String returnNumber);

}