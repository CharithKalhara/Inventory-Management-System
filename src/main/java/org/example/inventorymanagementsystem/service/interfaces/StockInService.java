package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.StockInRequest;
import org.example.inventorymanagementsystem.dto.response.StockInResponse;

import java.util.List;

public interface StockInService {

    StockInResponse create(StockInRequest request);

    StockInResponse update(Long id, StockInRequest request);

    StockInResponse getById(Long id);

    List<StockInResponse> getAll();

    void delete(Long id);
}