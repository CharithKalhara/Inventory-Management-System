package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.InvoiceRequest;
import org.example.inventorymanagementsystem.dto.response.InvoiceResponse;
import org.example.inventorymanagementsystem.service.interfaces.InvoiceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;


    @PostMapping
    public InvoiceResponse create(
            @RequestBody InvoiceRequest request) {

        return invoiceService.create(request);
    }


    @PutMapping("/{id}")
    public InvoiceResponse update(
            @PathVariable Long id,
            @RequestBody InvoiceRequest request) {

        return invoiceService.update(id, request);
    }


    @GetMapping("/{id}")
    public InvoiceResponse getById(
            @PathVariable Long id) {

        return invoiceService.getById(id);
    }


    @GetMapping
    public List<InvoiceResponse> getAll() {

        return invoiceService.getAll();
    }


    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        return;
    }
}