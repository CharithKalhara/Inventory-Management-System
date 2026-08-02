package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceRequest {

    private String invoiceNumber;

    private Long salesOrderId;

    private Double taxAmount;

    private String status;

    private List<InvoiceItemRequest> items;

}