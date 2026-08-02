package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.GoodsReceivedNoteRequest;
import org.example.inventorymanagementsystem.dto.response.GoodsReceivedNoteResponse;

import java.util.List;

public interface GoodsReceivedNoteService {

    GoodsReceivedNoteResponse create(GoodsReceivedNoteRequest request);

    GoodsReceivedNoteResponse update(Long id, GoodsReceivedNoteRequest request);

    GoodsReceivedNoteResponse getById(Long id);

    List<GoodsReceivedNoteResponse> getAll();

    void delete(Long id);

}