package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockInRequest {

    private Long stockId;

    private Integer quantity;

    private String referenceNo;

    private String remarks;
}