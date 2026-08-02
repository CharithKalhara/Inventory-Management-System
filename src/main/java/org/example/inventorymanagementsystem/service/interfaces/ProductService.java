package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.ProductRequest;
import org.example.inventorymanagementsystem.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    ProductResponse getById(Long id);

    List<ProductResponse> getAll();

    void delete(Long id);
}