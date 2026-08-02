package org.example.inventorymanagementsystem.controller;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.CustomerRequest;
import org.example.inventorymanagementsystem.dto.response.CustomerResponse;
import org.example.inventorymanagementsystem.service.interfaces.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public CustomerResponse create(@RequestBody CustomerRequest request) {
        return customerService.create(request);
    }

    @PutMapping("/{id}")
    public CustomerResponse update(@PathVariable Long id,
                                   @RequestBody CustomerRequest request) {
        return customerService.update(id, request);
    }

    @GetMapping("/{id}")
    public CustomerResponse getById(@PathVariable Long id) {
        return customerService.getById(id);
    }

    @GetMapping
    public List<CustomerResponse> getAll() {
        return customerService.getAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        customerService.delete(id);
    }
}