package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.PurchaseReturnRequest;
import org.example.inventorymanagementsystem.dto.response.PurchaseReturnResponse;
import org.example.inventorymanagementsystem.service.interfaces.PurchaseReturnService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/purchase-returns")
@RequiredArgsConstructor
public class PurchaseReturnController {


    private final PurchaseReturnService purchaseReturnService;


    @PostMapping
    public PurchaseReturnResponse create(
            @RequestBody PurchaseReturnRequest request) {

        return purchaseReturnService.create(request);
    }


    @PutMapping("/{id}")
    public PurchaseReturnResponse update(
            @PathVariable Long id,
            @RequestBody PurchaseReturnRequest request) {

        return purchaseReturnService.update(id, request);
    }


    @GetMapping("/{id}")
    public PurchaseReturnResponse getById(
            @PathVariable Long id) {

        return purchaseReturnService.getById(id);
    }


    @GetMapping
    public List<PurchaseReturnResponse> getAll() {

        return purchaseReturnService.getAll();
    }


    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        purchaseReturnService.delete(id);
    }

}