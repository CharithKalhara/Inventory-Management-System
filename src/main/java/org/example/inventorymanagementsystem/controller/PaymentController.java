package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.PaymentRequest;
import org.example.inventorymanagementsystem.dto.response.PaymentResponse;
import org.example.inventorymanagementsystem.service.interfaces.PaymentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;


    @PostMapping
    public PaymentResponse create(
            @RequestBody PaymentRequest request) {

        return paymentService.create(request);
    }


    @PutMapping("/{id}")
    public PaymentResponse update(
            @PathVariable Long id,
            @RequestBody PaymentRequest request) {

        return paymentService.update(id, request);
    }


    @GetMapping("/{id}")
    public PaymentResponse getById(
            @PathVariable Long id) {

        return paymentService.getById(id);
    }


    @GetMapping
    public List<PaymentResponse> getAll() {

        return paymentService.getAll();
    }


    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        paymentService.delete(id);
    }
}