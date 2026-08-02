package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseReturnRequest {

    private String returnNumber;

    private Long grnId;

    private String reason;

    private List<PurchaseReturnItemRequest> items;

}