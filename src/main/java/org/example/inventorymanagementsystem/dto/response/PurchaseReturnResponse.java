package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchaseReturnResponse {

    private Long id;

    private String returnNumber;

    private Long grnId;

    private String grnNumber;

    private String supplierName;

    private LocalDate returnDate;

    private String reason;

    private Double totalAmount;

    private List<PurchaseReturnItemResponse> items;

}