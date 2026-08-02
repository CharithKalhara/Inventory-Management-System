package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.StockTransferRequest;
import org.example.inventorymanagementsystem.dto.response.StockTransferResponse;

import java.util.List;

public interface StockTransferService {


    StockTransferResponse create(
            StockTransferRequest request);


    StockTransferResponse getById(
            Long id);


    List<StockTransferResponse> getAll();


    void delete(Long id);

}