package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.PurchaseOrderRequest;
import org.example.inventorymanagementsystem.dto.response.PurchaseOrderResponse;

import java.util.List;

public interface PurchaseOrderService {

    PurchaseOrderResponse create(PurchaseOrderRequest request);

    PurchaseOrderResponse update(Long id, PurchaseOrderRequest request);

    PurchaseOrderResponse getById(Long id);

    List<PurchaseOrderResponse> getAll();

    void delete(Long id);

}