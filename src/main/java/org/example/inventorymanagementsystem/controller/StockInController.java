package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.StockInRequest;
import org.example.inventorymanagementsystem.dto.response.StockInResponse;
import org.example.inventorymanagementsystem.service.interfaces.StockInService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-ins")
@RequiredArgsConstructor
public class StockInController {

    private final StockInService stockInService;

    @PostMapping
    public StockInResponse create(@RequestBody StockInRequest request) {
        return stockInService.create(request);
    }

    @PutMapping("/{id}")
    public StockInResponse update(@PathVariable Long id,
                                  @RequestBody StockInRequest request) {
        return stockInService.update(id, request);
    }

    @GetMapping("/{id}")
    public StockInResponse getById(@PathVariable Long id) {
        return stockInService.getById(id);
    }

    @GetMapping
    public List<StockInResponse> getAll() {
        return stockInService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        stockInService.delete(id);
    }
}