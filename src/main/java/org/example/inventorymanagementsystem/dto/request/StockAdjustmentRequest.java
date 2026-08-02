package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockAdjustmentRequest {


    private String adjustmentNumber;


    private Long warehouseId;


    private String reason;


    private List<StockAdjustmentItemRequest> items;

}