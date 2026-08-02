package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.StockTransferItemRequest;
import org.example.inventorymanagementsystem.dto.request.StockTransferRequest;
import org.example.inventorymanagementsystem.dto.response.StockTransferItemResponse;
import org.example.inventorymanagementsystem.dto.response.StockTransferResponse;
import org.example.inventorymanagementsystem.entity.StockTransfer;
import org.example.inventorymanagementsystem.entity.StockTransferItem;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StockTransferMapper {


    public StockTransfer toEntity(
            StockTransferRequest request) {

        return StockTransfer.builder()
                .transferNumber(
                        request.getTransferNumber()
                )
                .remarks(
                        request.getRemarks()
                )
                .build();
    }


    public StockTransferItem toItemEntity(
            StockTransferItemRequest request) {

        return StockTransferItem.builder()
                .quantity(
                        request.getQuantity()
                )
                .build();
    }


    public StockTransferResponse toResponse(
            StockTransfer transfer) {


        List<StockTransferItemResponse> items =
                transfer.getItems()
                        .stream()
                        .map(this::toItemResponse)
                        .toList();


        return StockTransferResponse.builder()
                .id(
                        transfer.getId()
                )
                .transferNumber(
                        transfer.getTransferNumber()
                )
                .fromWarehouseId(
                        transfer.getFromWarehouse().getId()
                )
                .fromWarehouseName(
                        transfer.getFromWarehouse().getName()
                )
                .toWarehouseId(
                        transfer.getToWarehouse().getId()
                )
                .toWarehouseName(
                        transfer.getToWarehouse().getName()
                )
                .transferDate(
                        transfer.getTransferDate()
                )
                .remarks(
                        transfer.getRemarks()
                )
                .items(items)
                .build();
    }


    public StockTransferItemResponse toItemResponse(
            StockTransferItem item) {

        return StockTransferItemResponse.builder()
                .id(
                        item.getId()
                )
                .productId(
                        item.getProduct().getId()
                )
                .productName(
                        item.getProduct().getName()
                )
                .quantity(
                        item.getQuantity()
                )
                .build();
    }

}