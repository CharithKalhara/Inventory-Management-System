package org.example.inventorymanagementsystem.dto.response;


import lombok.*;

import java.time.LocalDateTime;


@Getter
@Setter
@Builder
public class StockMovementResponse {


    private Long id;

    private String productName;

    private String warehouseName;

    private String type;

    private Integer quantity;

    private Integer previousQuantity;

    private Integer newQuantity;

    private String reference;

    private String remarks;

    private LocalDateTime createdAt;

}