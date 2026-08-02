package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.SalesOrderItemRequest;
import org.example.inventorymanagementsystem.dto.request.SalesOrderRequest;
import org.example.inventorymanagementsystem.dto.response.SalesOrderItemResponse;
import org.example.inventorymanagementsystem.dto.response.SalesOrderResponse;
import org.example.inventorymanagementsystem.entity.SalesOrder;
import org.example.inventorymanagementsystem.entity.SalesOrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SalesOrderMapper {


    public SalesOrder toEntity(SalesOrderRequest request) {

        return SalesOrder.builder()
                .soNumber(request.getSoNumber())
                .remarks(request.getRemarks())
                .build();
    }


    public SalesOrderItem toItemEntity(
            SalesOrderItemRequest request) {

        return SalesOrderItem.builder()
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .totalPrice(
                        request.getQuantity()
                                * request.getUnitPrice()
                )
                .build();
    }


    public SalesOrderResponse toResponse(
            SalesOrder salesOrder) {


        List<SalesOrderItemResponse> items =
                salesOrder.getItems()
                        .stream()
                        .map(this::toItemResponse)
                        .toList();


        return SalesOrderResponse.builder()
                .id(salesOrder.getId())
                .soNumber(salesOrder.getSoNumber())
                .customerId(
                        salesOrder.getCustomer().getId()
                )
                .customerName(
                        salesOrder.getCustomer().getName()
                )
                .orderDate(
                        salesOrder.getOrderDate()
                )
                .remarks(
                        salesOrder.getRemarks()
                )
                .totalAmount(
                        salesOrder.getTotalAmount()
                )
                .items(items)
                .build();
    }


    public SalesOrderItemResponse toItemResponse(
            SalesOrderItem item) {

        return SalesOrderItemResponse.builder()
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
            SalesOrder salesOrder,
            SalesOrderRequest request) {

        salesOrder.setSoNumber(
                request.getSoNumber()
        );

        salesOrder.setRemarks(
                request.getRemarks()
        );
    }
}