package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.SalesOrderRequest;
import org.example.inventorymanagementsystem.dto.response.SalesOrderResponse;

import java.util.List;

public interface SalesOrderService {

    SalesOrderResponse create(SalesOrderRequest request);

    SalesOrderResponse update(Long id, SalesOrderRequest request);

    SalesOrderResponse getById(Long id);

    List<SalesOrderResponse> getAll();

    void delete(Long id);

}