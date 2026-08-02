package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseOrderRequest {

    private String poNumber;

    private Long supplierId;

    private String remarks;

    private List<PurchaseOrderItemRequest> items;
}