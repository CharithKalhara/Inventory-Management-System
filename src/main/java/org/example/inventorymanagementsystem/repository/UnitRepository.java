package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.Unit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UnitRepository extends JpaRepository<Unit, Long> {

    boolean existsByName(String name);

    boolean existsByShortName(String shortName);

    Optional<Unit> findByName(String name);
}