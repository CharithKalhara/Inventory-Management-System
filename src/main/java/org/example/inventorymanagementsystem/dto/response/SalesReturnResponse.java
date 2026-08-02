package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesReturnResponse {

    private Long id;

    private String returnNumber;

    private Long invoiceId;

    private String invoiceNumber;

    private String customerName;

    private LocalDate returnDate;

    private String reason;

    private Double totalAmount;

    private List<SalesReturnItemResponse> items;

}