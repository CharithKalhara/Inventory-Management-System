package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StockAdjustmentResponse {


    private Long id;

    private String adjustmentNumber;

    private Long warehouseId;

    private String warehouseName;

    private LocalDate adjustmentDate;

    private String reason;

    private List<StockAdjustmentItemResponse> items;

}