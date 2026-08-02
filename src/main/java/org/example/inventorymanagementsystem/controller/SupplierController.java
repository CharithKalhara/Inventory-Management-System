package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.SupplierRequest;
import org.example.inventorymanagementsystem.dto.response.SupplierResponse;
import org.example.inventorymanagementsystem.service.interfaces.SupplierService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
@RequiredArgsConstructor
public class SupplierController {

    private final SupplierService supplierService;

    @PostMapping
    public SupplierResponse create(@RequestBody SupplierRequest request) {
        return supplierService.create(request);
    }

    @PutMapping("/{id}")
    public SupplierResponse update(@PathVariable Long id,
                                   @RequestBody SupplierRequest request) {
        return supplierService.update(id, request);
    }

    @GetMapping("/{id}")
    public SupplierResponse getById(@PathVariable Long id) {
        return supplierService.getById(id);
    }

    @GetMapping
    public List<SupplierResponse> getAll() {
        return supplierService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        supplierService.delete(id);
    }
}