package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.WarehouseRequest;
import org.example.inventorymanagementsystem.dto.response.WarehouseResponse;

import java.util.List;

public interface WarehouseService {


    WarehouseResponse create(
            WarehouseRequest request);


    WarehouseResponse update(
            Long id,
            WarehouseRequest request);


    WarehouseResponse getById(
            Long id);


    List<WarehouseResponse> getAll();


    void delete(Long id);

}