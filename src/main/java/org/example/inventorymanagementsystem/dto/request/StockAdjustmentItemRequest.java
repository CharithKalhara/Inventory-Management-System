package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAdjustmentItemRequest {


    private Long productId;


    private Integer countedQuantity;

}