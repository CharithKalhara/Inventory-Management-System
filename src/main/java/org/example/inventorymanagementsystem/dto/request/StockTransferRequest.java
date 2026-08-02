package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockTransferRequest {


    private String transferNumber;


    private Long fromWarehouseId;


    private Long toWarehouseId;


    private String remarks;


    private List<StockTransferItemRequest> items;

}