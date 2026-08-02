package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.SalesReturnItemRequest;
import org.example.inventorymanagementsystem.dto.request.SalesReturnRequest;
import org.example.inventorymanagementsystem.dto.response.SalesReturnItemResponse;
import org.example.inventorymanagementsystem.dto.response.SalesReturnResponse;
import org.example.inventorymanagementsystem.entity.SalesReturn;
import org.example.inventorymanagementsystem.entity.SalesReturnItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesReturnMapper {


    public SalesReturn toEntity(
            SalesReturnRequest request) {

        return SalesReturn.builder()
                .returnNumber(request.getReturnNumber())
                .reason(request.getReason())
                .build();
    }


    public SalesReturnItem toItemEntity(
            SalesReturnItemRequest request) {

        return SalesReturnItem.builder()
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .totalPrice(
                        request.getQuantity()
                                * request.getUnitPrice()
                )
                .build();
    }


    public SalesReturnResponse toResponse(
            SalesReturn salesReturn) {


        List<SalesReturnItemResponse> items =
                salesReturn.getItems()
                        .stream()
                        .map(this::toItemResponse)
                        .toList();


        return SalesReturnResponse.builder()
                .id(salesReturn.getId())
                .returnNumber(
                        salesReturn.getReturnNumber()
                )
                .invoiceId(
                        salesReturn.getInvoice().getId()
                )
                .invoiceNumber(
                        salesReturn.getInvoice()
                                .getInvoiceNumber()
                )
                .customerName(
                        salesReturn.getInvoice()
                                .getSalesOrder()
                                .getCustomer()
                                .getName()
                )
                .returnDate(
                        salesReturn.getReturnDate()
                )
                .reason(
                        salesReturn.getReason()
                )
                .totalAmount(
                        salesReturn.getTotalAmount()
                )
                .items(items)
                .build();
    }


    public SalesReturnItemResponse toItemResponse(
            SalesReturnItem item) {

        return SalesReturnItemResponse.builder()
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
            SalesReturn salesReturn,
            SalesReturnRequest request) {

        salesReturn.setReturnNumber(
                request.getReturnNumber()
        );

        salesReturn.setReason(
                request.getReason()
        );
    }

}