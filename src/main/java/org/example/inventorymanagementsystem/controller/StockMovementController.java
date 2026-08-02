package org.example.inventorymanagementsystem.controller;


import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.response.StockMovementResponse;
import org.example.inventorymanagementsystem.service.StockMovementService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/stock-movements")
@RequiredArgsConstructor
public class StockMovementController {


    private final StockMovementService service;


    @GetMapping
    public List<StockMovementResponse> getAll() {

        return service.getAll();
    }


    @GetMapping("/product/{productId}")
    public List<StockMovementResponse> getByProduct(
            @PathVariable Long productId) {

        return service.getByProduct(productId);
    }


    @GetMapping("/warehouse/{warehouseId}")
    public List<StockMovementResponse> getByWarehouse(
            @PathVariable Long warehouseId) {

        return service.getByWarehouse(warehouseId);
    }

}