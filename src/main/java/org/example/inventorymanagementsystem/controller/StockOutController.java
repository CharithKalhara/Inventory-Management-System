package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.StockOutRequest;
import org.example.inventorymanagementsystem.dto.response.StockOutResponse;
import org.example.inventorymanagementsystem.service.interfaces.StockOutService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-outs")
@RequiredArgsConstructor
public class StockOutController {

    private final StockOutService stockOutService;

    @PostMapping
    public StockOutResponse create(@RequestBody StockOutRequest request) {
        return stockOutService.create(request);
    }

    @PutMapping("/{id}")
    public StockOutResponse update(@PathVariable Long id,
                                   @RequestBody StockOutRequest request) {
        return stockOutService.update(id, request);
    }

    @GetMapping("/{id}")
    public StockOutResponse getById(@PathVariable Long id) {
        return stockOutService.getById(id);
    }

    @GetMapping
    public List<StockOutResponse> getAll() {
        return stockOutService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        stockOutService.delete(id);
    }
}