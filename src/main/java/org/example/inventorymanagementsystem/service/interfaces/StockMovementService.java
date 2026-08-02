package org.example.inventorymanagementsystem.service;

import org.example.inventorymanagementsystem.dto.response.StockMovementResponse;

import java.util.List;

public interface StockMovementService {


    List<StockMovementResponse> getAll();


    List<StockMovementResponse> getByProduct(Long productId);


    List<StockMovementResponse> getByWarehouse(Long warehouseId);

}