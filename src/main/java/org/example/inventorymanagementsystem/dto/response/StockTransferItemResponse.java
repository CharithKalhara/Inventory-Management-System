package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockTransferItemResponse {


    private Long id;


    private Long productId;


    private String productName;


    private Integer quantity;

}