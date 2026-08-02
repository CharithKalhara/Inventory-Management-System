package org.example.inventorymanagementsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductRequest {

    private String sku;

    private String name;

    private String description;

    private Long categoryId;

    private Long brandId;

    private Long unitId;

    private Long supplierId;

    private BigDecimal purchasePrice;

    private BigDecimal sellingPrice;

    private Integer minimumStock;

    private Boolean status;
}