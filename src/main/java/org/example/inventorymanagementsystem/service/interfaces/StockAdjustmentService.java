package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.StockAdjustmentRequest;
import org.example.inventorymanagementsystem.dto.response.StockAdjustmentResponse;

import java.util.List;

public interface StockAdjustmentService {


    StockAdjustmentResponse create(
            StockAdjustmentRequest request
    );


    StockAdjustmentResponse getById(
            Long id
    );


    List<StockAdjustmentResponse> getAll();


    void delete(
            Long id
    );

}