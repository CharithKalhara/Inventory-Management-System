package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockInResponse {

    private Long id;

    private Long stockId;

    private String productName;

    private Integer quantity;

    private String referenceNo;

    private String remarks;

    private LocalDateTime receivedDate;
}