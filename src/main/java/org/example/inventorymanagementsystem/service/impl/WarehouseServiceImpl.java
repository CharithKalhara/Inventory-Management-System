package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.WarehouseRequest;
import org.example.inventorymanagementsystem.dto.response.WarehouseResponse;
import org.example.inventorymanagementsystem.entity.Warehouse;
import org.example.inventorymanagementsystem.exception.WarehouseNotFoundException;
import org.example.inventorymanagementsystem.mapper.WarehouseMapper;
import org.example.inventorymanagementsystem.repository.WarehouseRepository;
import org.example.inventorymanagementsystem.service.interfaces.WarehouseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WarehouseServiceImpl
        implements WarehouseService {


    private final WarehouseRepository warehouseRepository;

    private final WarehouseMapper warehouseMapper;


    @Override
    public WarehouseResponse create(
            WarehouseRequest request) {


        Warehouse warehouse =
                warehouseMapper.toEntity(request);


        Warehouse saved =
                warehouseRepository.save(warehouse);


        return warehouseMapper.toResponse(saved);
    }


    @Override
    public WarehouseResponse update(
            Long id,
            WarehouseRequest request) {


        Warehouse warehouse =
                warehouseRepository.findById(id)
                        .orElseThrow(() ->
                                new WarehouseNotFoundException(
                                        "Warehouse not found: "
                                                + id
                                ));


        warehouseMapper.updateEntity(
                warehouse,
                request
        );


        return warehouseMapper.toResponse(
                warehouseRepository.save(warehouse)
        );
    }


    @Override
    public WarehouseResponse getById(
            Long id) {

        Warehouse warehouse =
                warehouseRepository.findById(id)
                        .orElseThrow(() ->
                                new WarehouseNotFoundException(
                                        "Warehouse not found: "
                                                + id
                                ));


        return warehouseMapper.toResponse(warehouse);
    }


    @Override
    public List<WarehouseResponse> getAll() {

        return warehouseRepository.findAll()
                .stream()
                .map(warehouseMapper::toResponse)
                .toList();
    }


    @Override
    public void delete(Long id) {

        warehouseRepository.deleteById(id);
    }

}