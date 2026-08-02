package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderResponse {

    private Long id;

    private String poNumber;

    private Long supplierId;

    private String supplierName;

    private LocalDate orderDate;

    private String remarks;

    private Double totalAmount;

    private List<PurchaseOrderItemResponse> items;
}