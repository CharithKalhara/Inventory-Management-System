package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.SalesReturnRequest;
import org.example.inventorymanagementsystem.dto.response.SalesReturnResponse;

import java.util.List;

public interface SalesReturnService {

    SalesReturnResponse create(SalesReturnRequest request);

    SalesReturnResponse update(Long id, SalesReturnRequest request);

    SalesReturnResponse getById(Long id);

    List<SalesReturnResponse> getAll();

    void delete(Long id);

}