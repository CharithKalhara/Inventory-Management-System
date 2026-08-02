package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.WarehouseRequest;
import org.example.inventorymanagementsystem.dto.response.WarehouseResponse;
import org.example.inventorymanagementsystem.service.interfaces.WarehouseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/warehouses")
@RequiredArgsConstructor
public class WarehouseController {


    private final WarehouseService warehouseService;


    @PostMapping
    public WarehouseResponse create(
            @RequestBody WarehouseRequest request) {

        return warehouseService.create(request);
    }


    @PutMapping("/{id}")
    public WarehouseResponse update(
            @PathVariable Long id,
            @RequestBody WarehouseRequest request) {

        return warehouseService.update(id, request);
    }


    @GetMapping("/{id}")
    public WarehouseResponse getById(
            @PathVariable Long id) {

        return warehouseService.getById(id);
    }


    @GetMapping
    public List<WarehouseResponse> getAll() {

        return warehouseService.getAll();
    }


    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        warehouseService.delete(id);
    }

}