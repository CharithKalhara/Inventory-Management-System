package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.StockRequest;
import org.example.inventorymanagementsystem.dto.response.StockResponse;
import org.example.inventorymanagementsystem.service.interfaces.StockService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stocks")
@RequiredArgsConstructor
public class StockController {

    private final StockService stockService;

    @PostMapping
    public StockResponse create(@RequestBody StockRequest request) {
        return stockService.create(request);
    }

    @PutMapping("/{id}")
    public StockResponse update(@PathVariable Long id,
                                @RequestBody StockRequest request) {
        return stockService.update(id, request);
    }

    @GetMapping("/{id}")
    public StockResponse getById(@PathVariable Long id) {
        return stockService.getById(id);
    }

    @GetMapping
    public List<StockResponse> getAll() {
        return stockService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        stockService.delete(id);
    }
}