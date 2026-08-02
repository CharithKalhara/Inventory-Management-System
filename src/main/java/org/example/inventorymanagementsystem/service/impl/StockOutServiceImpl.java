package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.StockOutRequest;
import org.example.inventorymanagementsystem.dto.response.StockOutResponse;
import org.example.inventorymanagementsystem.entity.Stock;
import org.example.inventorymanagementsystem.entity.StockOut;
import org.example.inventorymanagementsystem.exception.InsufficientStockException;
import org.example.inventorymanagementsystem.exception.StockNotFoundException;
import org.example.inventorymanagementsystem.exception.StockOutAlreadyExistsException;
import org.example.inventorymanagementsystem.exception.StockOutNotFoundException;
import org.example.inventorymanagementsystem.mapper.StockOutMapper;
import org.example.inventorymanagementsystem.repository.StockOutRepository;
import org.example.inventorymanagementsystem.repository.StockRepository;
import org.example.inventorymanagementsystem.service.interfaces.StockOutService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StockOutServiceImpl implements StockOutService {

    private final StockOutRepository stockOutRepository;
    private final StockRepository stockRepository;
    private final StockOutMapper stockOutMapper;

    @Override
    @Transactional
    public StockOutResponse create(StockOutRequest request) {

        if (stockOutRepository.existsByReferenceNo(request.getReferenceNo())) {
            throw new StockOutAlreadyExistsException(
                    "Reference number already exists: " + request.getReferenceNo());
        }

        Stock stock = stockRepository.findById(request.getStockId())
                .orElseThrow(() ->
                        new StockNotFoundException(
                                "Stock not found with id: " + request.getStockId()));

        if (stock.getAvailableQuantity() < request.getQuantity()) {
            throw new InsufficientStockException("Insufficient stock available.");
        }

        stock.setQuantity(stock.getQuantity() - request.getQuantity());
        stock.setAvailableQuantity(
                stock.getQuantity() - stock.getReservedQuantity());

        stockRepository.save(stock);

        StockOut stockOut = stockOutMapper.toEntity(request);
        stockOut.setStock(stock);
        stockOut.setIssuedDate(LocalDateTime.now());

        StockOut saved = stockOutRepository.save(stockOut);

        return stockOutMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public StockOutResponse update(Long id, StockOutRequest request) {

        StockOut stockOut = stockOutRepository.findById(id)
                .orElseThrow(() ->
                        new StockOutNotFoundException(
                                "Stock Out not found with id: " + id));

        Stock stock = stockOut.getStock();

        // Reverse previous transaction
        stock.setQuantity(stock.getQuantity() + stockOut.getQuantity());

        // Validate new quantity
        if ((stock.getQuantity() - stock.getReservedQuantity()) < request.getQuantity()) {
            throw new InsufficientStockException("Insufficient stock available.");
        }

        // Apply new transaction
        stock.setQuantity(stock.getQuantity() - request.getQuantity());
        stock.setAvailableQuantity(
                stock.getQuantity() - stock.getReservedQuantity());

        stockRepository.save(stock);

        stockOutMapper.updateEntity(stockOut, request);
        stockOut.setIssuedDate(LocalDateTime.now());

        StockOut updated = stockOutRepository.save(stockOut);

        return stockOutMapper.toResponse(updated);
    }

    @Override
    public StockOutResponse getById(Long id) {

        StockOut stockOut = stockOutRepository.findById(id)
                .orElseThrow(() ->
                        new StockOutNotFoundException(
                                "Stock Out not found with id: " + id));

        return stockOutMapper.toResponse(stockOut);
    }

    @Override
    public List<StockOutResponse> getAll() {

        return stockOutRepository.findAll()
                .stream()
                .map(stockOutMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {

        StockOut stockOut = stockOutRepository.findById(id)
                .orElseThrow(() ->
                        new StockOutNotFoundException(
                                "Stock Out not found with id: " + id));

        Stock stock = stockOut.getStock();

        // Restore stock
        stock.setQuantity(stock.getQuantity() + stockOut.getQuantity());
        stock.setAvailableQuantity(
                stock.getQuantity() - stock.getReservedQuantity());

        stockRepository.save(stock);

        stockOutRepository.delete(stockOut);
    }
}