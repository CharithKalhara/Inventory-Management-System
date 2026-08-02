package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReturnRequest {

    private String returnNumber;

    private Long invoiceId;

    private String reason;

    private List<SalesReturnItemRequest> items;

}