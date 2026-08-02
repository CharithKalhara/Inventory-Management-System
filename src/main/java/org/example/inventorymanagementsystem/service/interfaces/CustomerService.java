package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.CustomerRequest;
import org.example.inventorymanagementsystem.dto.response.CustomerResponse;

import java.util.List;

public interface CustomerService {

    CustomerResponse create(CustomerRequest request);

    CustomerResponse update(Long id, CustomerRequest request);

    CustomerResponse getById(Long id);

    List<CustomerResponse> getAll();

    void delete(Long id);

}