package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.StockAdjustmentRequest;
import org.example.inventorymanagementsystem.dto.response.StockAdjustmentResponse;
import org.example.inventorymanagementsystem.service.interfaces.StockAdjustmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-adjustments")
@RequiredArgsConstructor
public class StockAdjustmentController {


    private final StockAdjustmentService service;


    @PostMapping
    public StockAdjustmentResponse create(
            @RequestBody StockAdjustmentRequest request
    ) {

        return service.create(request);

    }


    @GetMapping("/{id}")
    public StockAdjustmentResponse getById(
            @PathVariable Long id
    ) {

        return service.getById(id);

    }


    @GetMapping
    public List<StockAdjustmentResponse> getAll() {

        return service.getAll();

    }


    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {

        service.delete(id);

    }

}