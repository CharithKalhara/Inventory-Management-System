package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.WarehouseLocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WarehouseLocationRepository
        extends JpaRepository<WarehouseLocation, Long> {

}