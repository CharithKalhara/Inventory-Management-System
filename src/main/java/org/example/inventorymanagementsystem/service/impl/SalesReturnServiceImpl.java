package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.SalesReturnItemRequest;
import org.example.inventorymanagementsystem.dto.request.SalesReturnRequest;
import org.example.inventorymanagementsystem.dto.response.SalesReturnResponse;
import org.example.inventorymanagementsystem.entity.*;
import org.example.inventorymanagementsystem.mapper.SalesReturnMapper;
import org.example.inventorymanagementsystem.repository.*;
import org.example.inventorymanagementsystem.service.interfaces.SalesReturnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SalesReturnServiceImpl
        implements SalesReturnService {


    private final SalesReturnRepository salesReturnRepository;
    private final InvoiceRepository invoiceRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final SalesReturnMapper salesReturnMapper;


    @Override
    @Transactional
    public SalesReturnResponse create(
            SalesReturnRequest request) {


        if (salesReturnRepository
                .existsByReturnNumber(request.getReturnNumber())) {

            throw new RuntimeException(
                    "Sales Return already exists: "
                            + request.getReturnNumber()
            );
        }


        Invoice invoice =
                invoiceRepository.findById(
                        request.getInvoiceId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "Invoice not found"
                        ));


        SalesReturn salesReturn =
                salesReturnMapper.toEntity(request);


        salesReturn.setInvoice(invoice);
        salesReturn.setReturnDate(LocalDate.now());


        double totalAmount = 0;


        for (SalesReturnItemRequest itemRequest :
                request.getItems()) {


            Product product =
                    productRepository.findById(
                            itemRequest.getProductId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Product not found"
                            ));


            Stock stock =
                    stockRepository.findByProductId(
                            product.getId()
                    ).orElseThrow(() ->
                            new RuntimeException(
                                    "Stock not found"
                            ));


            // Add returned quantity back to stock

            stock.setQuantity(
                    stock.getQuantity()
                            + itemRequest.getQuantity()
            );


            stock.setAvailableQuantity(
                    stock.getQuantity()
                            - stock.getReservedQuantity()
            );


            stockRepository.save(stock);



            SalesReturnItem item =
                    salesReturnMapper
                            .toItemEntity(itemRequest);


            item.setProduct(product);
            item.setSalesReturn(salesReturn);


            salesReturn.getItems()
                    .add(item);


            totalAmount += item.getTotalPrice();
        }


        salesReturn.setTotalAmount(totalAmount);


        SalesReturn saved =
                salesReturnRepository.save(salesReturn);


        return salesReturnMapper.toResponse(saved);
    }


    @Override
    public SalesReturnResponse update(
            Long id,
            SalesReturnRequest request) {

        throw new UnsupportedOperationException(
                "Update not implemented yet"
        );
    }


    @Override
    public SalesReturnResponse getById(Long id) {

        SalesReturn salesReturn =
                salesReturnRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Sales Return not found"
                                ));

        return salesReturnMapper.toResponse(salesReturn);
    }


    @Override
    public List<SalesReturnResponse> getAll() {

        return salesReturnRepository.findAll()
                .stream()
                .map(salesReturnMapper::toResponse)
                .toList();
    }


    @Override
    @Transactional
    public void delete(Long id) {

        SalesReturn salesReturn =
                salesReturnRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Sales Return not found"
                                ));

        salesReturnRepository.delete(salesReturn);
    }

}