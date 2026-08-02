package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.UnitRequest;
import org.example.inventorymanagementsystem.dto.response.UnitResponse;
import org.example.inventorymanagementsystem.entity.Unit;
import org.example.inventorymanagementsystem.exception.UnitAlreadyExistsException;
import org.example.inventorymanagementsystem.exception.UnitNotFoundException;
import org.example.inventorymanagementsystem.mapper.UnitMapper;
import org.example.inventorymanagementsystem.repository.UnitRepository;
import org.example.inventorymanagementsystem.service.interfaces.UnitService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    @Override
    public UnitResponse create(UnitRequest request) {

        if (unitRepository.existsByName(request.getName())) {
            throw new UnitAlreadyExistsException(
                    "Unit already exists with name: " + request.getName()
            );
        }

        if (unitRepository.existsByShortName(request.getShortName())) {
            throw new UnitAlreadyExistsException(
                    "Unit already exists with short name: " + request.getShortName()
            );
        }

        Unit unit = unitMapper.toEntity(request);

        Unit savedUnit = unitRepository.save(unit);

        return unitMapper.toResponse(savedUnit);
    }

    @Override
    public UnitResponse update(Long id, UnitRequest request) {

        Unit unit = unitRepository.findById(id)
                .orElseThrow(() ->
                        new UnitNotFoundException("Unit not found with id: " + id));

        unit.setName(request.getName());
        unit.setShortName(request.getShortName());
        unit.setDescription(request.getDescription());
        unit.setStatus(request.getStatus());

        Unit updatedUnit = unitRepository.save(unit);

        return unitMapper.toResponse(updatedUnit);
    }

    @Override
    public UnitResponse getById(Long id) {

        Unit unit = unitRepository.findById(id)
                .orElseThrow(() ->
                        new UnitNotFoundException("Unit not found with id: " + id));

        return unitMapper.toResponse(unit);
    }

    @Override
    public List<UnitResponse> getAll() {

        return unitRepository.findAll()
                .stream()
                .map(unitMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

        Unit unit = unitRepository.findById(id)
                .orElseThrow(() ->
                        new UnitNotFoundException("Unit not found with id: " + id));

        unitRepository.delete(unit);
    }
}