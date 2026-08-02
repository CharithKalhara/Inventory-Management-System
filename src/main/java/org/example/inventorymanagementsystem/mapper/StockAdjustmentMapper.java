package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.response.StockAdjustmentItemResponse;
import org.example.inventorymanagementsystem.dto.response.StockAdjustmentResponse;
import org.example.inventorymanagementsystem.entity.StockAdjustment;
import org.example.inventorymanagementsystem.entity.StockAdjustmentItem;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class StockAdjustmentMapper {


    public StockAdjustmentResponse toResponse(
            StockAdjustment adjustment
    ) {

        return StockAdjustmentResponse.builder()

                .id(adjustment.getId())

                .adjustmentNumber(
                        adjustment.getAdjustmentNumber()
                )

                .warehouseId(
                        adjustment.getWarehouse().getId()
                )

                .warehouseName(
                        adjustment.getWarehouse().getName()
                )

                .adjustmentDate(
                        adjustment.getAdjustmentDate()
                )

                .reason(
                        adjustment.getReason()
                )

                .items(
                        adjustment.getItems()
                                .stream()
                                .map(this::toItemResponse)
                                .collect(Collectors.toList())
                )

                .build();
    }



    private StockAdjustmentItemResponse toItemResponse(
            StockAdjustmentItem item
    ) {

        return StockAdjustmentItemResponse.builder()

                .id(item.getId())

                .productId(
                        item.getProduct().getId()
                )

                .productName(
                        item.getProduct().getName()
                )

                .systemQuantity(
                        item.getSystemQuantity()
                )

                .countedQuantity(
                        item.getCountedQuantity()
                )

                .difference(
                        item.getDifference()
                )

                .build();
    }

}