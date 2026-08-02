package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.SupplierRequest;
import org.example.inventorymanagementsystem.dto.response.SupplierResponse;

import java.util.List;

public interface SupplierService {

    SupplierResponse create(SupplierRequest request);

    SupplierResponse update(Long id, SupplierRequest request);

    SupplierResponse getById(Long id);

    List<SupplierResponse> getAll();

    void delete(Long id);
}