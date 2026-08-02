package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.StockTransferRequest;
import org.example.inventorymanagementsystem.dto.response.StockTransferResponse;
import org.example.inventorymanagementsystem.service.interfaces.StockTransferService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stock-transfers")
@RequiredArgsConstructor
public class StockTransferController {


    private final StockTransferService stockTransferService;


    @PostMapping
    public StockTransferResponse create(
            @RequestBody StockTransferRequest request) {

        return stockTransferService.create(request);
    }


    @GetMapping("/{id}")
    public StockTransferResponse getById(
            @PathVariable Long id) {

        return stockTransferService.getById(id);
    }


    @GetMapping
    public List<StockTransferResponse> getAll() {

        return stockTransferService.getAll();
    }


    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        stockTransferService.delete(id);
    }

}