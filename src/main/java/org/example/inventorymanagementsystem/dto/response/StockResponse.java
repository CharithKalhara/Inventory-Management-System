package org.example.inventorymanagementsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockResponse {

    private Long id;

    private Long productId;

    private String sku;

    private String productName;

    private Integer quantity;

    private Integer reservedQuantity;

    private Integer availableQuantity;
}