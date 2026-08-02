package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.PaymentRequest;
import org.example.inventorymanagementsystem.dto.response.PaymentResponse;
import org.example.inventorymanagementsystem.entity.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {


    public Payment toEntity(PaymentRequest request) {

        return Payment.builder()
                .paymentNumber(request.getPaymentNumber())
                .amount(request.getAmount())
                .paymentMethod(request.getPaymentMethod())
                .remarks(request.getRemarks())
                .build();
    }


    public PaymentResponse toResponse(Payment payment) {

        return PaymentResponse.builder()
                .id(payment.getId())
                .paymentNumber(payment.getPaymentNumber())
                .invoiceId(
                        payment.getInvoice().getId()
                )
                .invoiceNumber(
                        payment.getInvoice()
                                .getInvoiceNumber()
                )
                .customerName(
                        payment.getInvoice()
                                .getSalesOrder()
                                .getCustomer()
                                .getName()
                )
                .paymentDate(
                        payment.getPaymentDate()
                )
                .amount(
                        payment.getAmount()
                )
                .paymentMethod(
                        payment.getPaymentMethod()
                )
                .remarks(
                        payment.getRemarks()
                )
                .build();
    }


    public void updateEntity(
            Payment payment,
            PaymentRequest request) {

        payment.setAmount(
                request.getAmount()
        );

        payment.setPaymentMethod(
                request.getPaymentMethod()
        );

        payment.setRemarks(
                request.getRemarks()
        );
    }

}