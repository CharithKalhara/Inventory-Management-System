package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InvoiceResponse {

    private Long id;

    private String invoiceNumber;

    private Long salesOrderId;

    private String soNumber;

    private String customerName;

    private LocalDate invoiceDate;

    private Double taxAmount;

    private Double totalAmount;

    private String status;

    private List<InvoiceItemResponse> items;

}