package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.CustomerRequest;
import org.example.inventorymanagementsystem.dto.response.CustomerResponse;
import org.example.inventorymanagementsystem.entity.Customer;
import org.example.inventorymanagementsystem.exception.CustomerAlreadyExistsException;
import org.example.inventorymanagementsystem.exception.CustomerNotFoundException;
import org.example.inventorymanagementsystem.mapper.CustomerMapper;
import org.example.inventorymanagementsystem.repository.CustomerRepository;
import org.example.inventorymanagementsystem.service.interfaces.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public CustomerResponse create(CustomerRequest request) {

        if (customerRepository.existsByCustomerCode(request.getCustomerCode())) {
            throw new CustomerAlreadyExistsException(
                    "Customer code already exists: " + request.getCustomerCode());
        }

        Customer customer = customerMapper.toEntity(request);

        Customer saved = customerRepository.save(customer);

        return customerMapper.toResponse(saved);
    }

    @Override
    public CustomerResponse update(Long id, CustomerRequest request) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found with id: " + id));

        customerMapper.updateEntity(customer, request);

        Customer updated = customerRepository.save(customer);

        return customerMapper.toResponse(updated);
    }

    @Override
    public CustomerResponse getById(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found with id: " + id));

        return customerMapper.toResponse(customer);
    }

    @Override
    public List<CustomerResponse> getAll() {

        return customerRepository.findAll()
                .stream()
                .map(customerMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {

        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new CustomerNotFoundException(
                                "Customer not found with id: " + id));

        customerRepository.delete(customer);
    }
}