package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.SalesReturnRequest;
import org.example.inventorymanagementsystem.dto.response.SalesReturnResponse;
import org.example.inventorymanagementsystem.service.interfaces.SalesReturnService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales-returns")
@RequiredArgsConstructor
public class SalesReturnController {


    private final SalesReturnService salesReturnService;


    @PostMapping
    public SalesReturnResponse create(
            @RequestBody SalesReturnRequest request) {

        return salesReturnService.create(request);
    }


    @PutMapping("/{id}")
    public SalesReturnResponse update(
            @PathVariable Long id,
            @RequestBody SalesReturnRequest request) {

        return salesReturnService.update(id, request);
    }


    @GetMapping("/{id}")
    public SalesReturnResponse getById(
            @PathVariable Long id) {

        return salesReturnService.getById(id);
    }


    @GetMapping
    public List<SalesReturnResponse> getAll() {

        return salesReturnService.getAll();
    }


    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        salesReturnService.delete(id);
    }

}