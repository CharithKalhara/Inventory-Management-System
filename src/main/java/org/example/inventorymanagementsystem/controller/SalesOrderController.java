package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.SalesOrderRequest;
import org.example.inventorymanagementsystem.dto.response.SalesOrderResponse;
import org.example.inventorymanagementsystem.service.interfaces.SalesOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-orders")
@RequiredArgsConstructor
public class SalesOrderController {

    private final SalesOrderService salesOrderService;


    @PostMapping
    public SalesOrderResponse create(
            @RequestBody SalesOrderRequest request) {

        return salesOrderService.create(request);
    }


    @PutMapping("/{id}")
    public SalesOrderResponse update(
            @PathVariable Long id,
            @RequestBody SalesOrderRequest request) {

        return salesOrderService.update(id, request);
    }


    @GetMapping("/{id}")
    public SalesOrderResponse getById(
            @PathVariable Long id) {

        return salesOrderService.getById(id);
    }


    @GetMapping
    public List<SalesOrderResponse> getAll() {

        return salesOrderService.getAll();
    }


    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        salesOrderService.delete(id);
    }
}