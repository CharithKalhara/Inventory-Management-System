package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.InvoiceItemRequest;
import org.example.inventorymanagementsystem.dto.request.InvoiceRequest;
import org.example.inventorymanagementsystem.dto.response.InvoiceResponse;
import org.example.inventorymanagementsystem.entity.*;
import org.example.inventorymanagementsystem.exception.*;
import org.example.inventorymanagementsystem.mapper.InvoiceMapper;
import org.example.inventorymanagementsystem.repository.*;
import org.example.inventorymanagementsystem.service.interfaces.InvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final SalesOrderRepository salesOrderRepository;
    private final ProductRepository productRepository;
    private final InvoiceMapper invoiceMapper;


    @Override
    @Transactional
    public InvoiceResponse create(InvoiceRequest request) {

        if (invoiceRepository.existsByInvoiceNumber(
                request.getInvoiceNumber())) {

            throw new RuntimeException(
                    "Invoice already exists: "
                            + request.getInvoiceNumber());
        }


        SalesOrder salesOrder =
                salesOrderRepository.findById(
                        request.getSalesOrderId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Sales Order not found"));


        Invoice invoice =
                invoiceMapper.toEntity(request);


        invoice.setSalesOrder(salesOrder);
        invoice.setInvoiceDate(LocalDate.now());


        double totalAmount = 0;


        for (InvoiceItemRequest itemRequest :
                request.getItems()) {


            Product product =
                    productRepository.findById(
                            itemRequest.getProductId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Product not found"));


            InvoiceItem item =
                    invoiceMapper.toItemEntity(itemRequest);


            item.setProduct(product);
            item.setInvoice(invoice);


            invoice.getItems().add(item);


            totalAmount += item.getTotalPrice();
        }


        totalAmount += request.getTaxAmount();


        invoice.setTotalAmount(totalAmount);


        Invoice saved =
                invoiceRepository.save(invoice);


        return invoiceMapper.toResponse(saved);
    }


    @Override
    public InvoiceResponse update(Long id,
                                  InvoiceRequest request) {

        throw new UnsupportedOperationException(
                "Update not implemented yet");
    }


    @Override
    public InvoiceResponse getById(Long id) {

        Invoice invoice =
                invoiceRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invoice not found"));

        return invoiceMapper.toResponse(invoice);
    }


    @Override
    public List<InvoiceResponse> getAll() {

        return invoiceRepository.findAll()
                .stream()
                .map(invoiceMapper::toResponse)
                .toList();
    }


    @Override
    @Transactional
    public void delete(Long id) {

        Invoice invoice =
                invoiceRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Invoice not found"));

        invoiceRepository.delete(invoice);
    }
}