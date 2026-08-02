package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.CategoryRequest;
import org.example.inventorymanagementsystem.dto.response.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse create(CategoryRequest request);

    List<CategoryResponse> getAll();

    CategoryResponse getById(Long id);

    CategoryResponse update(Long id, CategoryRequest request);

    void delete(Long id);
}