package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.StockInRequest;
import org.example.inventorymanagementsystem.dto.response.StockInResponse;
import org.example.inventorymanagementsystem.entity.Stock;
import org.example.inventorymanagementsystem.entity.StockIn;
import org.example.inventorymanagementsystem.exception.StockInAlreadyExistsException;
import org.example.inventorymanagementsystem.exception.StockInNotFoundException;
import org.example.inventorymanagementsystem.exception.StockNotFoundException;
import org.example.inventorymanagementsystem.mapper.StockInMapper;
import org.example.inventorymanagementsystem.repository.StockInRepository;
import org.example.inventorymanagementsystem.repository.StockRepository;
import org.example.inventorymanagementsystem.service.interfaces.StockInService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockInServiceImpl implements StockInService {

    private final StockInRepository stockInRepository;
    private final StockRepository stockRepository;
    private final StockInMapper stockInMapper;

    @Override
    public StockInResponse create(StockInRequest request) {

        if (stockInRepository.existsByReferenceNo(request.getReferenceNo())) {
            throw new StockInAlreadyExistsException(
                    "Reference number already exists: " + request.getReferenceNo());
        }

        Stock stock = stockRepository.findById(request.getStockId())
                .orElseThrow(() ->
                        new StockNotFoundException(
                                "Stock not found with id: " + request.getStockId()));

        stock.setQuantity(stock.getQuantity() + request.getQuantity());
        stock.setAvailableQuantity(
                stock.getQuantity() - stock.getReservedQuantity());

        stockRepository.save(stock);

        StockIn stockIn = stockInMapper.toEntity(request);
        stockIn.setStock(stock);
        stockIn.setReceivedDate(LocalDateTime.now());

        StockIn savedStockIn = stockInRepository.save(stockIn);

        return stockInMapper.toResponse(savedStockIn);
    }

    @Override
    public StockInResponse update(Long id, StockInRequest request) {

        StockIn stockIn = stockInRepository.findById(id)
                .orElseThrow(() ->
                        new StockInNotFoundException(
                                "Stock In not found with id: " + id));

        Stock stock = stockRepository.findById(request.getStockId())
                .orElseThrow(() ->
                        new StockNotFoundException(
                                "Stock not found with id: " + request.getStockId()));

        // Reverse previous stock in
        stock.setQuantity(stock.getQuantity() - stockIn.getQuantity());

        // Apply new stock in
        stock.setQuantity(stock.getQuantity() + request.getQuantity());

        stock.setAvailableQuantity(
                stock.getQuantity() - stock.getReservedQuantity());

        stockRepository.save(stock);

        stockInMapper.updateEntity(stockIn, request);
        stockIn.setStock(stock);

        StockIn updatedStockIn = stockInRepository.save(stockIn);

        return stockInMapper.toResponse(updatedStockIn);
    }

    @Override
    public StockInResponse getById(Long id) {

        StockIn stockIn = stockInRepository.findById(id)
                .orElseThrow(() ->
                        new StockInNotFoundException(
                                "Stock In not found with id: " + id));

        return stockInMapper.toResponse(stockIn);
    }

    @Override
    public List<StockInResponse> getAll() {

        return stockInRepository.findAll()
                .stream()
                .map(stockInMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {

        StockIn stockIn = stockInRepository.findById(id)
                .orElseThrow(() ->
                        new StockInNotFoundException(
                                "Stock In not found with id: " + id));

        Stock stock = stockIn.getStock();

        stock.setQuantity(stock.getQuantity() - stockIn.getQuantity());
        stock.setAvailableQuantity(
                stock.getQuantity() - stock.getReservedQuantity());

        stockRepository.save(stock);

        stockInRepository.delete(stockIn);
    }
}