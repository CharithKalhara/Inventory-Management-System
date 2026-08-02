package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.PurchaseOrderRequest;
import org.example.inventorymanagementsystem.dto.response.PurchaseOrderResponse;
import org.example.inventorymanagementsystem.service.interfaces.PurchaseOrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-orders")
@RequiredArgsConstructor
public class PurchaseOrderController {

    private final PurchaseOrderService purchaseOrderService;

    @PostMapping
    public PurchaseOrderResponse create(
            @RequestBody PurchaseOrderRequest request) {

        return purchaseOrderService.create(request);
    }


    @PutMapping("/{id}")
    public PurchaseOrderResponse update(
            @PathVariable Long id,
            @RequestBody PurchaseOrderRequest request) {

        return purchaseOrderService.update(id, request);
    }


    @GetMapping("/{id}")
    public PurchaseOrderResponse getById(
            @PathVariable Long id) {

        return purchaseOrderService.getById(id);
    }


    @GetMapping
    public List<PurchaseOrderResponse> getAll() {

        return purchaseOrderService.getAll();
    }


    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        purchaseOrderService.delete(id);
    }
}