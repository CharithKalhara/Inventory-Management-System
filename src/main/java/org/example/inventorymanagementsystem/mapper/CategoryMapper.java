package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.CategoryRequest;
import org.example.inventorymanagementsystem.dto.response.CategoryResponse;
import org.example.inventorymanagementsystem.entity.Category;

public class CategoryMapper {

    private CategoryMapper() {
        // Prevent instantiation
    }

    public static Category toEntity(CategoryRequest request) {

        Category category = new Category();

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        return category;
    }

    public static CategoryResponse toResponse(Category category) {

        return new CategoryResponse(
                category.getId(),
                category.getName(),
                category.getDescription()
        );
    }
}