package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.response.StockMovementResponse;
import org.example.inventorymanagementsystem.entity.StockMovement;
import org.springframework.stereotype.Component;


@Component
public class StockMovementMapper {


    public StockMovementResponse toResponse(
            StockMovement movement) {


        return StockMovementResponse.builder()

                .id(movement.getId())

                .productName(
                        movement.getProduct()
                                .getName()
                )

                .warehouseName(
                        movement.getWarehouse()
                                .getName()
                )

                .type(
                        movement.getType().name()
                )

                .quantity(
                        movement.getQuantity()
                )

                .previousQuantity(
                        movement.getPreviousQuantity()
                )

                .newQuantity(
                        movement.getNewQuantity()
                )

                .reference(
                        movement.getReference()
                )

                .createdAt(
                        movement.getCreatedAt()
                )

                .build();
    }

}