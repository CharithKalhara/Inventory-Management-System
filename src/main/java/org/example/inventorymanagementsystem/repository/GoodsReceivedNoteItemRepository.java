package org.example.inventorymanagementsystem.repository;

import org.example.inventorymanagementsystem.entity.GoodsReceivedNoteItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GoodsReceivedNoteItemRepository
        extends JpaRepository<GoodsReceivedNoteItem, Long> {

}