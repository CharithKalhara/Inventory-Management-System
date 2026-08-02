package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.ProductRequest;
import org.example.inventorymanagementsystem.dto.response.ProductResponse;
import org.example.inventorymanagementsystem.entity.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {

        return Product.builder()
                .sku(request.getSku())
                .name(request.getName())
                .description(request.getDescription())
                .purchasePrice(request.getPurchasePrice())
                .sellingPrice(request.getSellingPrice())
                .minimumStock(request.getMinimumStock())
                .status(request.getStatus())
                .build();
    }

    public ProductResponse toResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .sku(product.getSku())
                .name(product.getName())
                .description(product.getDescription())

                // Category
                .categoryId(product.getCategory().getId())
                .categoryName(product.getCategory().getName())

                // Brand
                .brandId(product.getBrand().getId())
                .brandName(product.getBrand().getName())

                // Unit
                .unitId(product.getUnit().getId())
                .unitName(product.getUnit().getName())

                // Supplier
                .supplierId(product.getSupplier().getId())
                .supplierName(product.getSupplier().getName())

                .purchasePrice(product.getPurchasePrice())
                .sellingPrice(product.getSellingPrice())
                .minimumStock(product.getMinimumStock())
                .status(product.getStatus())
                .build();
    }

    public void updateEntity(Product product, ProductRequest request) {

        product.setSku(request.getSku());
        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPurchasePrice(request.getPurchasePrice());
        product.setSellingPrice(request.getSellingPrice());
        product.setMinimumStock(request.getMinimumStock());
        product.setStatus(request.getStatus());
    }
}