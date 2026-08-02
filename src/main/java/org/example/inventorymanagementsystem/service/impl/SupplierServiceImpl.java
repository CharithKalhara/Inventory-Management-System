package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.SupplierRequest;
import org.example.inventorymanagementsystem.dto.response.SupplierResponse;
import org.example.inventorymanagementsystem.entity.Supplier;
import org.example.inventorymanagementsystem.exception.SupplierAlreadyExistsException;
import org.example.inventorymanagementsystem.exception.SupplierNotFoundException;
import org.example.inventorymanagementsystem.mapper.SupplierMapper;
import org.example.inventorymanagementsystem.repository.SupplierRepository;
import org.example.inventorymanagementsystem.service.interfaces.SupplierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    @Override
    public SupplierResponse create(SupplierRequest request) {

        if (supplierRepository.existsByName(request.getName())) {
            throw new SupplierAlreadyExistsException(
                    "Supplier already exists with name: " + request.getName());
        }

        if (request.getEmail() != null && !request.getEmail().isBlank()
                && supplierRepository.existsByEmail(request.getEmail())) {
            throw new SupplierAlreadyExistsException(
                    "Supplier already exists with email: " + request.getEmail());
        }

        Supplier supplier = supplierMapper.toEntity(request);
        Supplier savedSupplier = supplierRepository.save(supplier);

        return supplierMapper.toResponse(savedSupplier);
    }

    @Override
    public SupplierResponse update(Long id, SupplierRequest request) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new SupplierNotFoundException("Supplier not found with id: " + id));

        if (!supplier.getName().equals(request.getName())
                && supplierRepository.existsByName(request.getName())) {
            throw new SupplierAlreadyExistsException(
                    "Supplier already exists with name: " + request.getName());
        }

        if (request.getEmail() != null && !request.getEmail().isBlank()
                && !request.getEmail().equals(supplier.getEmail())
                && supplierRepository.existsByEmail(request.getEmail())) {
            throw new SupplierAlreadyExistsException(
                    "Supplier already exists with email: " + request.getEmail());
        }

        supplierMapper.updateEntity(supplier, request);

        Supplier updatedSupplier = supplierRepository.save(supplier);

        return supplierMapper.toResponse(updatedSupplier);
    }

    @Override
    public SupplierResponse getById(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new SupplierNotFoundException("Supplier not found with id: " + id));

        return supplierMapper.toResponse(supplier);
    }

    @Override
    public List<SupplierResponse> getAll() {

        return supplierRepository.findAll()
                .stream()
                .map(supplierMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {

        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() ->
                        new SupplierNotFoundException("Supplier not found with id: " + id));

        supplierRepository.delete(supplier);
    }
}