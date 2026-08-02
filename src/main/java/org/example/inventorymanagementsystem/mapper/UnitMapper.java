package org.example.inventorymanagementsystem.mapper;

import org.example.inventorymanagementsystem.dto.request.UnitRequest;
import org.example.inventorymanagementsystem.dto.response.UnitResponse;
import org.example.inventorymanagementsystem.entity.Unit;
import org.springframework.stereotype.Component;

@Component
public class UnitMapper {

    public Unit toEntity(UnitRequest request) {

        return Unit.builder()
                .name(request.getName())
                .shortName(request.getShortName())
                .description(request.getDescription())
                .status(request.getStatus())
                .build();
    }

    public UnitResponse toResponse(Unit unit) {

        return UnitResponse.builder()
                .id(unit.getId())
                .name(unit.getName())
                .shortName(unit.getShortName())
                .description(unit.getDescription())
                .status(unit.getStatus())
                .build();
    }

    public void updateEntity(Unit unit, UnitRequest request) {

        unit.setName(request.getName());
        unit.setShortName(request.getShortName());
        unit.setDescription(request.getDescription());
        unit.setStatus(request.getStatus());
    }
}