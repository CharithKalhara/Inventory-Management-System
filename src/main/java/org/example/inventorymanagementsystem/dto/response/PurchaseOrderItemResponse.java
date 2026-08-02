package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderItemResponse {

    private Long id;

    private Long productId;

    private String productName;

    private Integer quantity;

    private Double unitPrice;

    private Double totalPrice;
}