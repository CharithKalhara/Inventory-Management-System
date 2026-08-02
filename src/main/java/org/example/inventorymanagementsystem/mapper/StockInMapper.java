package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.StockInRequest;
import org.example.inventorymanagementsystem.dto.response.StockInResponse;
import org.example.inventorymanagementsystem.entity.StockIn;
import org.springframework.stereotype.Component;

@Component
public class StockInMapper {

    public StockIn toEntity(StockInRequest request) {

        return StockIn.builder()
                .quantity(request.getQuantity())
                .referenceNo(request.getReferenceNo())
                .remarks(request.getRemarks())
                .build();
    }

    public StockInResponse toResponse(StockIn stockIn) {

        return StockInResponse.builder()
                .id(stockIn.getId())
                .stockId(stockIn.getStock().getId())
                .productName(stockIn.getStock().getProduct().getName())
                .quantity(stockIn.getQuantity())
                .referenceNo(stockIn.getReferenceNo())
                .remarks(stockIn.getRemarks())
                .receivedDate(stockIn.getReceivedDate())
                .build();
    }

    public void updateEntity(StockIn stockIn, StockInRequest request) {

        stockIn.setQuantity(request.getQuantity());
        stockIn.setReferenceNo(request.getReferenceNo());
        stockIn.setRemarks(request.getRemarks());
    }
}