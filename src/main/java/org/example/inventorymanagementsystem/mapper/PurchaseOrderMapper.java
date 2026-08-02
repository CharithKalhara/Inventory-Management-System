package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.PurchaseOrderItemRequest;
import org.example.inventorymanagementsystem.dto.request.PurchaseOrderRequest;
import org.example.inventorymanagementsystem.dto.response.PurchaseOrderItemResponse;
import org.example.inventorymanagementsystem.dto.response.PurchaseOrderResponse;
import org.example.inventorymanagementsystem.entity.PurchaseOrder;
import org.example.inventorymanagementsystem.entity.PurchaseOrderItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseOrderMapper {


    public PurchaseOrder toEntity(PurchaseOrderRequest request) {

        return PurchaseOrder.builder()
                .poNumber(request.getPoNumber())
                .remarks(request.getRemarks())
                .build();
    }


    public PurchaseOrderItem toItemEntity(PurchaseOrderItemRequest request) {

        return PurchaseOrderItem.builder()
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .totalPrice(
                        request.getQuantity() * request.getUnitPrice()
                )
                .build();
    }


    public PurchaseOrderResponse toResponse(
            PurchaseOrder purchaseOrder) {


        List<PurchaseOrderItemResponse> items =
                purchaseOrder.getItems()
                        .stream()
                        .map(this::toItemResponse)
                        .toList();


        return PurchaseOrderResponse.builder()
                .id(purchaseOrder.getId())
                .poNumber(purchaseOrder.getPoNumber())
                .supplierId(
                        purchaseOrder.getSupplier().getId()
                )
                .supplierName(
                        purchaseOrder.getSupplier().getName()
                )
                .orderDate(
                        purchaseOrder.getOrderDate()
                )
                .remarks(
                        purchaseOrder.getRemarks()
                )
                .totalAmount(
                        purchaseOrder.getTotalAmount()
                )
                .items(items)
                .build();
    }


    public PurchaseOrderItemResponse toItemResponse(
            PurchaseOrderItem item) {


        return PurchaseOrderItemResponse.builder()
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
            PurchaseOrder purchaseOrder,
            PurchaseOrderRequest request) {


        purchaseOrder.setPoNumber(
                request.getPoNumber()
        );

        purchaseOrder.setRemarks(
                request.getRemarks()
        );
    }
}