package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseReturnItemRequest {

    private Long productId;

    private Integer quantity;

    private Double unitPrice;

}