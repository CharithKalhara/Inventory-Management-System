package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrderResponse {

    private Long id;

    private String soNumber;

    private Long customerId;

    private String customerName;

    private LocalDate orderDate;

    private String remarks;

    private Double totalAmount;

    private List<SalesOrderItemResponse> items;

}