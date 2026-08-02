package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.CustomerRequest;
import org.example.inventorymanagementsystem.dto.response.CustomerResponse;
import org.example.inventorymanagementsystem.entity.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {

    public Customer toEntity(CustomerRequest request) {

        return Customer.builder()
                .customerCode(request.getCustomerCode())
                .name(request.getName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .active(request.getActive())
                .build();
    }

    public CustomerResponse toResponse(Customer customer) {

        return CustomerResponse.builder()
                .id(customer.getId())
                .customerCode(customer.getCustomerCode())
                .name(customer.getName())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .address(customer.getAddress())
                .active(customer.getActive())
                .build();
    }

    public void updateEntity(Customer customer, CustomerRequest request) {

        customer.setCustomerCode(request.getCustomerCode());
        customer.setName(request.getName());
        customer.setEmail(request.getEmail());
        customer.setPhone(request.getPhone());
        customer.setAddress(request.getAddress());
        customer.setActive(request.getActive());
    }
}