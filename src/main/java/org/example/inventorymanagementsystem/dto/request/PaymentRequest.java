package org.example.inventorymanagementsystem.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequest {

    private String paymentNumber;

    private Long invoiceId;

    private Double amount;

    private String paymentMethod;

    private String remarks;

}