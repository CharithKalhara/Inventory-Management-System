package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.StockRequest;
import org.example.inventorymanagementsystem.dto.response.StockResponse;
import org.example.inventorymanagementsystem.entity.Product;
import org.example.inventorymanagementsystem.entity.Stock;
import org.example.inventorymanagementsystem.exception.ProductNotFoundException;
import org.example.inventorymanagementsystem.exception.StockAlreadyExistsException;
import org.example.inventorymanagementsystem.exception.StockNotFoundException;
import org.example.inventorymanagementsystem.mapper.StockMapper;
import org.example.inventorymanagementsystem.repository.ProductRepository;
import org.example.inventorymanagementsystem.repository.StockRepository;
import org.example.inventorymanagementsystem.service.interfaces.StockService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StockServiceImpl implements StockService {

    private final StockRepository stockRepository;
    private final ProductRepository productRepository;
    private final StockMapper stockMapper;

    @Override
    public StockResponse create(StockRequest request) {

        if (stockRepository.existsByProductId(request.getProductId())) {
            throw new StockAlreadyExistsException(
                    "Stock already exists for product id: " + request.getProductId());
        }

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + request.getProductId()));

        Stock stock = stockMapper.toEntity(request);
        stock.setProduct(product);

        Stock savedStock = stockRepository.save(stock);

        return stockMapper.toResponse(savedStock);
    }

    @Override
    public StockResponse update(Long id, StockRequest request) {

        Stock stock = stockRepository.findById(id)
                .orElseThrow(() ->
                        new StockNotFoundException("Stock not found with id: " + id));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() ->
                        new ProductNotFoundException(
                                "Product not found with id: " + request.getProductId()));

        stockMapper.updateEntity(stock, request);
        stock.setProduct(product);

        Stock updatedStock = stockRepository.save(stock);

        return stockMapper.toResponse(updatedStock);
    }

    @Override
    public StockResponse getById(Long id) {

        Stock stock = stockRepository.findById(id)
                .orElseThrow(() ->
                        new StockNotFoundException("Stock not found with id: " + id));

        return stockMapper.toResponse(stock);
    }

    @Override
    public List<StockResponse> getAll() {

        return stockRepository.findAll()
                .stream()
                .map(stockMapper::toResponse)
                .toList();
    }

    @Override
    public void delete(Long id) {

        Stock stock = stockRepository.findById(id)
                .orElseThrow(() ->
                        new StockNotFoundException("Stock not found with id: " + id));

        stockRepository.delete(stock);
    }
}