package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.InvoiceItemRequest;
import org.example.inventorymanagementsystem.dto.request.InvoiceRequest;
import org.example.inventorymanagementsystem.dto.response.InvoiceItemResponse;
import org.example.inventorymanagementsystem.dto.response.InvoiceResponse;
import org.example.inventorymanagementsystem.entity.Invoice;
import org.example.inventorymanagementsystem.entity.InvoiceItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InvoiceMapper {


    public Invoice toEntity(InvoiceRequest request) {

        return Invoice.builder()
                .invoiceNumber(request.getInvoiceNumber())
                .taxAmount(request.getTaxAmount())
                .status(request.getStatus())
                .build();
    }


    public InvoiceItem toItemEntity(
            InvoiceItemRequest request) {

        return InvoiceItem.builder()
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .totalPrice(
                        request.getQuantity()
                                * request.getUnitPrice()
                )
                .build();
    }


    public InvoiceResponse toResponse(
            Invoice invoice) {


        List<InvoiceItemResponse> items =
                invoice.getItems()
                        .stream()
                        .map(this::toItemResponse)
                        .toList();


        return InvoiceResponse.builder()
                .id(invoice.getId())
                .invoiceNumber(
                        invoice.getInvoiceNumber()
                )
                .salesOrderId(
                        invoice.getSalesOrder().getId()
                )
                .soNumber(
                        invoice.getSalesOrder().getSoNumber()
                )
                .customerName(
                        invoice.getSalesOrder()
                                .getCustomer()
                                .getName()
                )
                .invoiceDate(
                        invoice.getInvoiceDate()
                )
                .taxAmount(
                        invoice.getTaxAmount()
                )
                .totalAmount(
                        invoice.getTotalAmount()
                )
                .status(
                        invoice.getStatus()
                )
                .items(items)
                .build();
    }


    public InvoiceItemResponse toItemResponse(
            InvoiceItem item) {

        return InvoiceItemResponse.builder()
                .id(item.getId())
                .productId(
                        item.getProduct().getId()
                )
                .productName(
                        item.getProduct().getName()
                )
                .quantity(
                        item.getQuantity()
                )
                .unitPrice(
                        item.getUnitPrice()
                )
                .totalPrice(
                        item.getTotalPrice()
                )
                .build();
    }


    public void updateEntity(
            Invoice invoice,
            InvoiceRequest request) {

        invoice.setInvoiceNumber(
                request.getInvoiceNumber()
        );

        invoice.setTaxAmount(
                request.getTaxAmount()
        );

        invoice.setStatus(
                request.getStatus()
        );
    }
}