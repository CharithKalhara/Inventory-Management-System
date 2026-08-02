package org.example.inventorymanagementsystem.service.impl;

import org.example.inventorymanagementsystem.entity.Category;
import org.example.inventorymanagementsystem.exception.CategoryAlreadyExistsException;
import org.example.inventorymanagementsystem.exception.CategoryNotFoundException;
import org.example.inventorymanagementsystem.repository.CategoryRepository;
import org.example.inventorymanagementsystem.service.interfaces.CategoryService;
import org.springframework.stereotype.Service;
import org.example.inventorymanagementsystem.dto.request.CategoryRequest;
import org.example.inventorymanagementsystem.dto.response.CategoryResponse;
import org.example.inventorymanagementsystem.mapper.CategoryMapper;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {

        if (categoryRepository.existsByName(request.getName())) {
            throw new CategoryAlreadyExistsException("Category already exists.");
        }

        Category category = CategoryMapper.toEntity(request);

        Category savedCategory = categoryRepository.save(category);

        return CategoryMapper.toResponse(savedCategory);
    }

    @Override
    public List<CategoryResponse> getAll() {

        return categoryRepository.findAll()
                .stream()
                .map(CategoryMapper::toResponse)
                .toList();
    }

    @Override
    public CategoryResponse getById(Long id) {

        return CategoryMapper.toResponse(findCategoryById(id));
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {

        Category category = findCategoryById(id);

        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updatedCategory = categoryRepository.save(category);

        return CategoryMapper.toResponse(updatedCategory);
    }

    @Override
    public void delete(Long id) {

        Category category = findCategoryById(id);

        categoryRepository.delete(category);
    }

    private Category findCategoryById(Long id) {

        return categoryRepository.findById(id)
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found."));
    }
}