package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.ProductRequest;
import org.example.inventorymanagementsystem.dto.response.ProductResponse;
import org.example.inventorymanagementsystem.entity.*;
import org.example.inventorymanagementsystem.exception.*;
import org.example.inventorymanagementsystem.mapper.ProductMapper;
import org.example.inventorymanagementsystem.repository.*;
import org.example.inventorymanagementsystem.service.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final BrandRepository brandRepository;
    private final UnitRepository unitRepository;
    private final SupplierRepository supplierRepository;
    private final ProductMapper productMapper;

    @Override
    public ProductResponse create(ProductRequest request) {

        if (productRepository.existsBySku(request.getSku())) {
            throw new ProductAlreadyExistsException(
                    "Product already exists with SKU: " + request.getSku());
        }

        if (productRepository.existsByName(request.getName())) {
            throw new ProductAlreadyExistsException(
                    "Product already exists with name: " + request.getName());
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found with id: " + request.getCategoryId()));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() ->
                        new BrandNotFoundException("Brand not found with id: " + request.getBrandId()));

        Unit unit = unitRepository.findById(request.getUnitId())
                .orElseThrow(() ->
                        new UnitNotFoundException("Unit not found with id: " + request.getUnitId()));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() ->
                        new SupplierNotFoundException("Supplier not found with id: " + request.getSupplierId()));

        Product product = productMapper.toEntity(request);

        product.setCategory(category);
        product.setBrand(brand);
        product.setUnit(unit);
        product.setSupplier(supplier);

        Product savedProduct = productRepository.save(product);

        return productMapper.toResponse(savedProduct);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id));

        if (!product.getSku().equals(request.getSku())
                && productRepository.existsBySku(request.getSku())) {
            throw new ProductAlreadyExistsException(
                    "Product already exists with SKU: " + request.getSku());
        }

        if (!product.getName().equals(request.getName())
                && productRepository.existsByName(request.getName())) {
            throw new ProductAlreadyExistsException(
                    "Product already exists with name: " + request.getName());
        }

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() ->
                        new CategoryNotFoundException("Category not found with id: " + request.getCategoryId()));

        Brand brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() ->
                        new BrandNotFoundException("Brand not found with id: " + request.getBrandId()));

        Unit unit = unitRepository.findById(request.getUnitId())
                .orElseThrow(() ->
                        new UnitNotFoundException("Unit not found with id: " + request.getUnitId()));

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() ->
                        new SupplierNotFoundException("Supplier not found with id: " + request.getSupplierId()));

        productMapper.updateEntity(product, request);

        product.setCategory(category);
        product.setBrand(brand);
        product.setUnit(unit);
        product.setSupplier(supplier);

        Product updatedProduct = productRepository.save(product);

        return productMapper.toResponse(updatedProduct);
    }

    @Override
    public ProductResponse getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id));

        return productMapper.toResponse(product);
    }

    @Override
    public List<ProductResponse> getAll() {

        return productRepository.findAll()
                .stream()
                .map(productMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ProductNotFoundException("Product not found with id: " + id));

        productRepository.delete(product);
    }
}