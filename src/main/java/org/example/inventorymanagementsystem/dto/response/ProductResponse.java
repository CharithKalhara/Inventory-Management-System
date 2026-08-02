package org.example.inventorymanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Builder;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {

    private Long id;

    private String sku;

    private String name;

    private String description;

    // Category
    private Long categoryId;
    private String categoryName;

    // Brand
    private Long brandId;
    private String brandName;

    // Supplier
    private Long supplierId;
    private String supplierName;

    // Unit
    private Long unitId;
    private String unitName;

    private BigDecimal purchasePrice;

    private BigDecimal sellingPrice;

    private Integer minimumStock;

    private Boolean status;
}