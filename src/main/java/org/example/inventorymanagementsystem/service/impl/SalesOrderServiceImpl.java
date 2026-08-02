package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.SalesOrderItemRequest;
import org.example.inventorymanagementsystem.dto.request.SalesOrderRequest;
import org.example.inventorymanagementsystem.dto.response.SalesOrderResponse;
import org.example.inventorymanagementsystem.entity.*;
import org.example.inventorymanagementsystem.exception.*;
import org.example.inventorymanagementsystem.mapper.SalesOrderMapper;
import org.example.inventorymanagementsystem.repository.*;
import org.example.inventorymanagementsystem.service.interfaces.SalesOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesOrderServiceImpl implements SalesOrderService {

    private final SalesOrderRepository salesOrderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final SalesOrderMapper salesOrderMapper;


    @Override
    @Transactional
    public SalesOrderResponse create(SalesOrderRequest request) {

        if (salesOrderRepository.existsBySoNumber(request.getSoNumber())) {
            throw new RuntimeException(
                    "Sales Order already exists: "
                            + request.getSoNumber());
        }


        Customer customer =
                customerRepository.findById(request.getCustomerId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Customer not found"));


        SalesOrder salesOrder =
                salesOrderMapper.toEntity(request);

        salesOrder.setCustomer(customer);
        salesOrder.setOrderDate(LocalDate.now());


        double totalAmount = 0;


        for (SalesOrderItemRequest itemRequest :
                request.getItems()) {


            Product product =
                    productRepository.findById(
                            itemRequest.getProductId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Product not found"));


            Stock stock =
                    stockRepository.findByProductId(
                            product.getId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Stock not found"));


            if (stock.getAvailableQuantity()
                    < itemRequest.getQuantity()) {

                throw new RuntimeException(
                        "Insufficient stock for "
                                + product.getName());
            }


            // Reserve stock
            stock.setReservedQuantity(
                    stock.getReservedQuantity()
                            + itemRequest.getQuantity()
            );

            stock.setAvailableQuantity(
                    stock.getQuantity()
                            - stock.getReservedQuantity()
            );

            stockRepository.save(stock);


            SalesOrderItem item =
                    salesOrderMapper.toItemEntity(itemRequest);


            item.setProduct(product);
            item.setSalesOrder(salesOrder);


            salesOrder.getItems().add(item);


            totalAmount += item.getTotalPrice();
        }


        salesOrder.setTotalAmount(totalAmount);


        SalesOrder saved =
                salesOrderRepository.save(salesOrder);


        return salesOrderMapper.toResponse(saved);
    }


    @Override
    public SalesOrderResponse update(Long id,
                                     SalesOrderRequest request) {

        throw new UnsupportedOperationException(
                "Update not implemented yet");
    }


    @Override
    public SalesOrderResponse getById(Long id) {

        SalesOrder salesOrder =
                salesOrderRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Sales Order not found"));

        return salesOrderMapper.toResponse(salesOrder);
    }


    @Override
    public List<SalesOrderResponse> getAll() {

        return salesOrderRepository.findAll()
                .stream()
                .map(salesOrderMapper::toResponse)
                .toList();
    }


    @Override
    @Transactional
    public void delete(Long id) {

        SalesOrder salesOrder =
                salesOrderRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Sales Order not found"));


        salesOrderRepository.delete(salesOrder);
    }
}