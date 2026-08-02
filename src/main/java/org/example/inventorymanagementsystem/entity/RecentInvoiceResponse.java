package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecentInvoiceResponse {


    private String invoiceNumber;

    private String customerName;

    private LocalDate invoiceDate;

    private Double totalAmount;

    private String status;

}