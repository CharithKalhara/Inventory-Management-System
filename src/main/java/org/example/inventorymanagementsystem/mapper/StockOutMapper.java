package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.StockOutRequest;
import org.example.inventorymanagementsystem.dto.response.StockOutResponse;
import org.example.inventorymanagementsystem.entity.StockOut;
import org.springframework.stereotype.Component;

@Component
public class StockOutMapper {

    public StockOut toEntity(StockOutRequest request) {

        return StockOut.builder()
                .quantity(request.getQuantity())
                .referenceNo(request.getReferenceNo())
                .remarks(request.getRemarks())
                .build();
    }

    public StockOutResponse toResponse(StockOut stockOut) {

        return StockOutResponse.builder()
                .id(stockOut.getId())
                .stockId(stockOut.getStock().getId())
                .productName(stockOut.getStock().getProduct().getName())
                .quantity(stockOut.getQuantity())
                .referenceNo(stockOut.getReferenceNo())
                .remarks(stockOut.getRemarks())
                .issuedDate(stockOut.getIssuedDate())
                .build();
    }

    public void updateEntity(StockOut stockOut, StockOutRequest request) {

        stockOut.setQuantity(request.getQuantity());
        stockOut.setReferenceNo(request.getReferenceNo());
        stockOut.setRemarks(request.getRemarks());
    }

}