package org.example.inventorymanagementsystem.dto.request;

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
public class StockRequest {

    private Long productId;

    private Integer quantity;

    private Integer reservedQuantity;

    private Integer availableQuantity;
}