package org.example.inventorymanagementsystem.service.impl;


import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.response.StockMovementResponse;
import org.example.inventorymanagementsystem.entity.StockMovement;
import org.example.inventorymanagementsystem.mapper.StockMovementMapper;
import org.example.inventorymanagementsystem.repository.StockMovementRepository;
import org.example.inventorymanagementsystem.service.StockMovementService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
@RequiredArgsConstructor
public class StockMovementServiceImpl
        implements StockMovementService {


    private final StockMovementRepository repository;

    private final StockMovementMapper mapper;



    @Override
    public List<StockMovementResponse> getAll() {

        return repository.findAll()
                .stream()
                .map(mapper::toResponse)
                .toList();
    }



    @Override
    public List<StockMovementResponse> getByProduct(
            Long productId) {

        return repository.findByProductId(productId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }



    @Override
    public List<StockMovementResponse> getByWarehouse(
            Long warehouseId) {

        return repository.findByWarehouseId(warehouseId)
                .stream()
                .map(mapper::toResponse)
                .toList();
    }

}