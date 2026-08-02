package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.StockOutRequest;
import org.example.inventorymanagementsystem.dto.response.StockOutResponse;

import java.util.List;

public interface StockOutService {

    StockOutResponse create(StockOutRequest request);

    StockOutResponse update(Long id, StockOutRequest request);

    StockOutResponse getById(Long id);

    List<StockOutResponse> getAll();

    void delete(Long id);

}