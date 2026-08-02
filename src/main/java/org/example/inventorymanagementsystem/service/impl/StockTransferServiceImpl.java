package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.StockTransferItemRequest;
import org.example.inventorymanagementsystem.dto.request.StockTransferRequest;
import org.example.inventorymanagementsystem.dto.response.StockTransferResponse;
import org.example.inventorymanagementsystem.entity.*;
import org.example.inventorymanagementsystem.mapper.StockTransferMapper;
import org.example.inventorymanagementsystem.repository.*;
import org.example.inventorymanagementsystem.service.interfaces.StockTransferService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockTransferServiceImpl
        implements StockTransferService {


    private final StockTransferRepository stockTransferRepository;

    private final WarehouseRepository warehouseRepository;

    private final ProductRepository productRepository;

    private final StockRepository stockRepository;

    private final StockMovementRepository stockMovementRepository;

    private final StockTransferMapper stockTransferMapper;



    @Override
    @Transactional
    public StockTransferResponse create(
            StockTransferRequest request) {


        if (stockTransferRepository.existsByTransferNumber(
                request.getTransferNumber())) {

            throw new RuntimeException(
                    "Transfer already exists"
            );
        }


        Warehouse fromWarehouse =
                warehouseRepository.findById(
                        request.getFromWarehouseId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Source warehouse not found"
                        ));


        Warehouse toWarehouse =
                warehouseRepository.findById(
                        request.getToWarehouseId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Destination warehouse not found"
                        ));



        StockTransfer transfer =
                stockTransferMapper.toEntity(request);


        transfer.setFromWarehouse(fromWarehouse);
        transfer.setToWarehouse(toWarehouse);
        transfer.setTransferDate(LocalDate.now());



        for (StockTransferItemRequest itemRequest :
                request.getItems()) {


            Product product =
                    productRepository.findById(
                            itemRequest.getProductId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Product not found"
                            ));



            // SOURCE STOCK

            Stock sourceStock =
                    stockRepository
                            .findByProductIdAndWarehouseId(
                                    product.getId(),
                                    fromWarehouse.getId()
                            )
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Source stock not found"
                                    ));



            int oldSourceQuantity =
                    sourceStock.getQuantity();



            if(oldSourceQuantity <
                    itemRequest.getQuantity()) {

                throw new RuntimeException(
                        "Insufficient stock"
                );
            }



            sourceStock.setQuantity(
                    oldSourceQuantity -
                            itemRequest.getQuantity()
            );


            sourceStock.setAvailableQuantity(
                    sourceStock.getQuantity()
                            -
                            sourceStock.getReservedQuantity()
            );


            stockRepository.save(sourceStock);



            // AUDIT OUT

            StockMovement outMovement =
                    StockMovement.builder()
                            .product(product)
                            .warehouse(fromWarehouse)
                            .type(MovementType.TRANSFER_OUT)
                            .quantity(
                                    itemRequest.getQuantity()
                            )
                            .previousQuantity(
                                    oldSourceQuantity
                            )
                            .newQuantity(
                                    sourceStock.getQuantity()
                            )
                            .reference(
                                    request.getTransferNumber()
                            )
                            .createdAt(
                                    LocalDateTime.now()
                            )
                            .build();


            stockMovementRepository.save(outMovement);




            // DESTINATION STOCK

            Stock destinationStock =
                    stockRepository
                            .findByProductIdAndWarehouseId(
                                    product.getId(),
                                    toWarehouse.getId()
                            )
                            .orElse(null);



            int oldDestinationQuantity = 0;


            if(destinationStock == null) {

                destinationStock =
                        Stock.builder()
                                .product(product)
                                .warehouse(toWarehouse)
                                .quantity(0)
                                .reservedQuantity(0)
                                .availableQuantity(0)
                                .build();

            } else {

                oldDestinationQuantity =
                        destinationStock.getQuantity();
            }



            destinationStock.setQuantity(
                    destinationStock.getQuantity()
                            +
                            itemRequest.getQuantity()
            );


            destinationStock.setAvailableQuantity(
                    destinationStock.getQuantity()
                            -
                            destinationStock.getReservedQuantity()
            );


            stockRepository.save(destinationStock);



            // AUDIT IN

            StockMovement inMovement =
                    StockMovement.builder()
                            .product(product)
                            .warehouse(toWarehouse)
                            .type(MovementType.TRANSFER_IN)
                            .quantity(
                                    itemRequest.getQuantity()
                            )
                            .previousQuantity(
                                    oldDestinationQuantity
                            )
                            .newQuantity(
                                    destinationStock.getQuantity()
                            )
                            .reference(
                                    request.getTransferNumber()
                            )
                            .createdAt(
                                    LocalDateTime.now()
                            )
                            .build();



            stockMovementRepository.save(inMovement);



            StockTransferItem item =
                    stockTransferMapper
                            .toItemEntity(itemRequest);


            item.setProduct(product);
            item.setStockTransfer(transfer);


            transfer.getItems().add(item);
        }



        StockTransfer saved =
                stockTransferRepository.save(transfer);


        return stockTransferMapper.toResponse(saved);
    }




    @Override
    public StockTransferResponse getById(Long id) {

        return stockTransferRepository.findById(id)
                .map(stockTransferMapper::toResponse)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Transfer not found"
                        ));
    }



    @Override
    public List<StockTransferResponse> getAll() {

        return stockTransferRepository.findAll()
                .stream()
                .map(stockTransferMapper::toResponse)
                .toList();
    }



    @Override
    public void delete(Long id) {

        stockTransferRepository.deleteById(id);
    }

}