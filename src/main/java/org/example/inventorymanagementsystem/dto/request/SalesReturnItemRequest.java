package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReturnItemRequest {

    private Long productId;

    private Integer quantity;

    private Double unitPrice;

}