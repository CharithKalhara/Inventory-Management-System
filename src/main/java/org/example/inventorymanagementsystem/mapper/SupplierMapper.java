package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.SupplierRequest;
import org.example.inventorymanagementsystem.dto.response.SupplierResponse;
import org.example.inventorymanagementsystem.entity.Supplier;
import org.springframework.stereotype.Component;

@Component
public class SupplierMapper {

    public Supplier toEntity(SupplierRequest request) {

        return Supplier.builder()
                .name(request.getName())
                .contactPerson(request.getContactPerson())
                .phone(request.getPhone())
                .email(request.getEmail())
                .address(request.getAddress())
                .status(request.getStatus())
                .build();
    }

    public SupplierResponse toResponse(Supplier supplier) {

        return SupplierResponse.builder()
                .id(supplier.getId())
                .name(supplier.getName())
                .contactPerson(supplier.getContactPerson())
                .phone(supplier.getPhone())
                .email(supplier.getEmail())
                .address(supplier.getAddress())
                .status(supplier.getStatus())
                .build();
    }

    public void updateEntity(Supplier supplier, SupplierRequest request) {

        supplier.setName(request.getName());
        supplier.setContactPerson(request.getContactPerson());
        supplier.setPhone(request.getPhone());
        supplier.setEmail(request.getEmail());
        supplier.setAddress(request.getAddress());
        supplier.setStatus(request.getStatus());
    }
}