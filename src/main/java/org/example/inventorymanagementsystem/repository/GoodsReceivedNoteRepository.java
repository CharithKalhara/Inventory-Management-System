package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.GoodsReceivedNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsReceivedNoteRepository
        extends JpaRepository<GoodsReceivedNote, Long> {

    boolean existsByGrnNumber(String grnNumber);

}