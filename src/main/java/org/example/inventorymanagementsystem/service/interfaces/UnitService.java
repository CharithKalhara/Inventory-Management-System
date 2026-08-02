package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.UnitRequest;
import org.example.inventorymanagementsystem.dto.response.UnitResponse;

import java.util.List;

public interface UnitService {

    UnitResponse create(UnitRequest request);

    UnitResponse update(Long id, UnitRequest request);

    UnitResponse getById(Long id);

    List<UnitResponse> getAll();

    void delete(Long id);
}