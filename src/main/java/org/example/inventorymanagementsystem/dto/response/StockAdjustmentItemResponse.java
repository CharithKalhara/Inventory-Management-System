package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockAdjustmentItemResponse {


    private Long id;


    private Long productId;


    private String productName;


    private Integer systemQuantity;


    private Integer countedQuantity;


    private Integer difference;

}