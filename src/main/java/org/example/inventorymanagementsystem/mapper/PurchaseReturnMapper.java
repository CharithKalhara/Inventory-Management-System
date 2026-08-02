package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.PurchaseReturnItemRequest;
import org.example.inventorymanagementsystem.dto.request.PurchaseReturnRequest;
import org.example.inventorymanagementsystem.dto.response.PurchaseReturnItemResponse;
import org.example.inventorymanagementsystem.dto.response.PurchaseReturnResponse;
import org.example.inventorymanagementsystem.entity.PurchaseReturn;
import org.example.inventorymanagementsystem.entity.PurchaseReturnItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PurchaseReturnMapper {


    public PurchaseReturn toEntity(
            PurchaseReturnRequest request) {

        return PurchaseReturn.builder()
                .returnNumber(request.getReturnNumber())
                .reason(request.getReason())
                .build();
    }


    public PurchaseReturnItem toItemEntity(
            PurchaseReturnItemRequest request) {

        return PurchaseReturnItem.builder()
                .quantity(request.getQuantity())
                .unitPrice(request.getUnitPrice())
                .totalPrice(
                        request.getQuantity()
                                * request.getUnitPrice()
                )
                .build();
    }


    public PurchaseReturnResponse toResponse(
            PurchaseReturn purchaseReturn) {


        List<PurchaseReturnItemResponse> items =
                purchaseReturn.getItems()
                        .stream()
                        .map(this::toItemResponse)
                        .toList();


        return PurchaseReturnResponse.builder()
                .id(purchaseReturn.getId())
                .returnNumber(
                        purchaseReturn.getReturnNumber()
                )
                .grnId(
                        purchaseReturn.getGoodsReceivedNote()
                                .getId()
                )
                .grnNumber(
                        purchaseReturn.getGoodsReceivedNote()
                                .getGrnNumber()
                )
                .supplierName(
                        purchaseReturn.getGoodsReceivedNote()
                                .getPurchaseOrder()
                                .getSupplier()
                                .getName()
                )
                .returnDate(
                        purchaseReturn.getReturnDate()
                )
                .reason(
                        purchaseReturn.getReason()
                )
                .totalAmount(
                        purchaseReturn.getTotalAmount()
                )
                .items(items)
                .build();
    }


    public PurchaseReturnItemResponse toItemResponse(
            PurchaseReturnItem item) {

        return PurchaseReturnItemResponse.builder()
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
            PurchaseReturn purchaseReturn,
            PurchaseReturnRequest request) {

        purchaseReturn.setReturnNumber(
                request.getReturnNumber()
        );

        purchaseReturn.setReason(
                request.getReason()
        );
    }

}