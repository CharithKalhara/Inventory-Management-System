package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.WarehouseRequest;
import org.example.inventorymanagementsystem.dto.response.WarehouseLocationResponse;
import org.example.inventorymanagementsystem.dto.response.WarehouseResponse;
import org.example.inventorymanagementsystem.entity.Warehouse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WarehouseMapper {


    public Warehouse toEntity(
            WarehouseRequest request) {

        return Warehouse.builder()
                .code(request.getCode())
                .name(request.getName())
                .address(request.getAddress())
                .active(request.getActive())
                .build();
    }


    public WarehouseResponse toResponse(
            Warehouse warehouse) {


        List<WarehouseLocationResponse> locations =
                warehouse.getLocations()
                        .stream()
                        .map(location ->
                                WarehouseLocationResponse.builder()
                                        .id(location.getId())
                                        .locationCode(
                                                location.getLocationCode()
                                        )
                                        .description(
                                                location.getDescription()
                                        )
                                        .build()
                        )
                        .toList();


        return WarehouseResponse.builder()
                .id(warehouse.getId())
                .code(warehouse.getCode())
                .name(warehouse.getName())
                .address(warehouse.getAddress())
                .active(warehouse.getActive())
                .locations(locations)
                .build();
    }


    public void updateEntity(
            Warehouse warehouse,
            WarehouseRequest request) {

        warehouse.setCode(
                request.getCode()
        );

        warehouse.setName(
                request.getName()
        );

        warehouse.setAddress(
                request.getAddress()
        );

        warehouse.setActive(
                request.getActive()
        );
    }

}