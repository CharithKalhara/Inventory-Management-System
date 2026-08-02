package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.StockRequest;
import org.example.inventorymanagementsystem.dto.response.StockResponse;
import org.example.inventorymanagementsystem.entity.Stock;
import org.springframework.stereotype.Component;

@Component
public class StockMapper {

    public Stock toEntity(StockRequest request) {

        return Stock.builder()
                .quantity(request.getQuantity())
                .reservedQuantity(request.getReservedQuantity())
                .availableQuantity(request.getAvailableQuantity())
                .build();
    }

    public StockResponse toResponse(Stock stock) {

        return StockResponse.builder()
                .id(stock.getId())
                .productId(stock.getProduct().getId())
                .sku(stock.getProduct().getSku())
                .productName(stock.getProduct().getName())
                .quantity(stock.getQuantity())
                .reservedQuantity(stock.getReservedQuantity())
                .availableQuantity(stock.getAvailableQuantity())
                .build();
    }

    public void updateEntity(Stock stock, StockRequest request) {

        stock.setQuantity(request.getQuantity());
        stock.setReservedQuantity(request.getReservedQuantity());
        stock.setAvailableQuantity(request.getAvailableQuantity());
    }
}