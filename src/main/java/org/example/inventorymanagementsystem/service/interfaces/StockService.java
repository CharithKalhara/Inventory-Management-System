package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.StockRequest;
import org.example.inventorymanagementsystem.dto.response.StockResponse;

import java.util.List;

public interface StockService {

    StockResponse create(StockRequest request);

    StockResponse update(Long id, StockRequest request);

    StockResponse getById(Long id);

    List<StockResponse> getAll();

    void delete(Long id);
}
