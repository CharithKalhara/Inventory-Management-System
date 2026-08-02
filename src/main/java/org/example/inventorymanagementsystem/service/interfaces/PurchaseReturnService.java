package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.PurchaseReturnRequest;
import org.example.inventorymanagementsystem.dto.response.PurchaseReturnResponse;

import java.util.List;

public interface PurchaseReturnService {

    PurchaseReturnResponse create(PurchaseReturnRequest request);

    PurchaseReturnResponse update(Long id, PurchaseReturnRequest request);

    PurchaseReturnResponse getById(Long id);

    List<PurchaseReturnResponse> getAll();

    void delete(Long id);

}