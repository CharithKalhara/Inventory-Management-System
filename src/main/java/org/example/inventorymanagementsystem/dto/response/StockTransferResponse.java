package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockTransferResponse {


    private Long id;


    private String transferNumber;


    private Long fromWarehouseId;


    private String fromWarehouseName;


    private Long toWarehouseId;


    private String toWarehouseName;


    private LocalDate transferDate;


    private String remarks;


    private List<StockTransferItemResponse> items;

}