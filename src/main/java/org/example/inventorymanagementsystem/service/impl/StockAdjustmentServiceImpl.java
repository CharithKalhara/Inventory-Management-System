package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.StockAdjustmentItemRequest;
import org.example.inventorymanagementsystem.dto.request.StockAdjustmentRequest;
import org.example.inventorymanagementsystem.dto.response.StockAdjustmentResponse;
import org.example.inventorymanagementsystem.entity.*;
import org.example.inventorymanagementsystem.mapper.StockAdjustmentMapper;
import org.example.inventorymanagementsystem.repository.*;
import org.example.inventorymanagementsystem.service.interfaces.StockAdjustmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockAdjustmentServiceImpl
        implements StockAdjustmentService {


    private final StockAdjustmentRepository adjustmentRepository;

    private final WarehouseRepository warehouseRepository;

    private final ProductRepository productRepository;

    private final StockRepository stockRepository;

    private final StockMovementRepository stockMovementRepository;

    private final StockAdjustmentMapper mapper;



    @Override
    @Transactional
    public StockAdjustmentResponse create(
            StockAdjustmentRequest request) {


        if (adjustmentRepository.existsByAdjustmentNumber(
                request.getAdjustmentNumber())) {

            throw new RuntimeException(
                    "Adjustment already exists"
            );
        }


        Warehouse warehouse =
                warehouseRepository.findById(
                        request.getWarehouseId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Warehouse not found"
                        ));


        StockAdjustment adjustment =
                StockAdjustment.builder()
                        .adjustmentNumber(
                                request.getAdjustmentNumber()
                        )
                        .warehouse(warehouse)
                        .adjustmentDate(
                                LocalDate.now()
                        )
                        .reason(
                                request.getReason()
                        )
                        .items(new ArrayList<>())
                        .build();



        for (StockAdjustmentItemRequest itemRequest :
                request.getItems()) {


            Product product =
                    productRepository.findById(
                            itemRequest.getProductId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Product not found"
                            ));



            Stock stock =
                    stockRepository
                            .findByProductIdAndWarehouseId(
                                    product.getId(),
                                    warehouse.getId()
                            )
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Stock not found"
                                    ));



            Integer previousQuantity =
                    stock.getQuantity();


            Integer countedQuantity =
                    itemRequest.getCountedQuantity();


            Integer difference =
                    countedQuantity - previousQuantity;



            stock.setQuantity(
                    countedQuantity
            );


            Integer reserved =
                    stock.getReservedQuantity() == null
                            ? 0
                            : stock.getReservedQuantity();


            stock.setAvailableQuantity(
                    countedQuantity - reserved
            );


            stockRepository.save(stock);



            StockMovement movement =
                    StockMovement.builder()
                            .product(product)
                            .warehouse(warehouse)
                            .type(
                                    MovementType.ADJUSTMENT
                            )
                            .quantity(
                                    Math.abs(difference)
                            )
                            .previousQuantity(
                                    previousQuantity
                            )
                            .newQuantity(
                                    countedQuantity
                            )
                            .reference(
                                    request.getAdjustmentNumber()
                            )
                            .build();


            stockMovementRepository.save(
                    movement
            );



            StockAdjustmentItem item =
                    StockAdjustmentItem.builder()
                            .product(product)
                            .stockAdjustment(adjustment)
                            .systemQuantity(
                                    previousQuantity
                            )
                            .countedQuantity(
                                    countedQuantity
                            )
                            .difference(
                                    difference
                            )
                            .build();


            adjustment.getItems().add(item);

        }


        StockAdjustment saved =
                adjustmentRepository.save(
                        adjustment
                );


        return mapper.toResponse(saved);
    }



    @Override
    public StockAdjustmentResponse getById(Long id) {

        StockAdjustment adjustment =
                adjustmentRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Adjustment not found"
                                ));


        return mapper.toResponse(adjustment);
    }



    @Override
    public List<StockAdjustmentResponse> getAll() {

        return adjustmentRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }



    @Override
    public void delete(Long id) {

        adjustmentRepository.deleteById(id);
    }

}