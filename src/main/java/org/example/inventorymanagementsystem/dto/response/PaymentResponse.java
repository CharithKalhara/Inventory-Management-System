package org.example.inventorymanagementsystem.dto.response;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponse {

    private Long id;

    private String paymentNumber;

    private Long invoiceId;

    private String invoiceNumber;

    private String customerName;

    private LocalDate paymentDate;

    private Double amount;

    private String paymentMethod;

    private String remarks;

}