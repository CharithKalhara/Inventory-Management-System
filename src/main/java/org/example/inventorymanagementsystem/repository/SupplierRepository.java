package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    boolean existsByName(String name);

    boolean existsByEmail(String email);

    Optional<Supplier> findByName(String name);
}