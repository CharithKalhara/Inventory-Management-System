package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalesOrderRequest {

    private String soNumber;

    private Long customerId;

    private String remarks;

    private List<SalesOrderItemRequest> items;

}