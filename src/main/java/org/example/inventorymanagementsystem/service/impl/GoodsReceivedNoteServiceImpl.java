package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.GoodsReceivedNoteItemRequest;
import org.example.inventorymanagementsystem.dto.request.GoodsReceivedNoteRequest;
import org.example.inventorymanagementsystem.dto.response.GoodsReceivedNoteResponse;
import org.example.inventorymanagementsystem.entity.*;
import org.example.inventorymanagementsystem.exception.*;
import org.example.inventorymanagementsystem.mapper.GoodsReceivedNoteMapper;
import org.example.inventorymanagementsystem.repository.*;
import org.example.inventorymanagementsystem.service.interfaces.GoodsReceivedNoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodsReceivedNoteServiceImpl implements GoodsReceivedNoteService {

    private final GoodsReceivedNoteRepository grnRepository;
    private final PurchaseOrderRepository purchaseOrderRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final GoodsReceivedNoteMapper grnMapper;


    @Override
    @Transactional
    public GoodsReceivedNoteResponse create(
            GoodsReceivedNoteRequest request) {


        if (grnRepository.existsByGrnNumber(request.getGrnNumber())) {
            throw new RuntimeException(
                    "GRN already exists: " + request.getGrnNumber()
            );
        }


        PurchaseOrder purchaseOrder =
                purchaseOrderRepository.findById(request.getPurchaseOrderId())
                        .orElseThrow(() ->
                                new PurchaseOrderNotFoundException(
                                        "Purchase Order not found with id: "
                                                + request.getPurchaseOrderId()
                                ));


        GoodsReceivedNote grn =
                grnMapper.toEntity(request);

        grn.setPurchaseOrder(purchaseOrder);
        grn.setReceivedDate(LocalDate.now());


        for (GoodsReceivedNoteItemRequest itemRequest :
                request.getItems()) {


            Product product =
                    productRepository.findById(
                            itemRequest.getProductId()
                    ).orElseThrow(() ->
                            new ProductNotFoundException(
                                    "Product not found with id: "
                                            + itemRequest.getProductId()
                            ));


            GoodsReceivedNoteItem item =
                    grnMapper.toItemEntity(itemRequest);


            item.setProduct(product);
            item.setGoodsReceivedNote(grn);


            grn.getItems().add(item);


            // Update stock
            Stock stock =
                    stockRepository.findByProductId(product.getId())
                            .orElseThrow(() ->
                                    new StockNotFoundException(
                                            "Stock not found for product: "
                                                    + product.getName()
                                    ));


            stock.setQuantity(
                    stock.getQuantity()
                            + itemRequest.getQuantityReceived()
            );


            stock.setAvailableQuantity(
                    stock.getQuantity()
                            - stock.getReservedQuantity()
            );


            stockRepository.save(stock);
        }


        GoodsReceivedNote saved =
                grnRepository.save(grn);


        return grnMapper.toResponse(saved);
    }


    @Override
    public GoodsReceivedNoteResponse update(
            Long id,
            GoodsReceivedNoteRequest request) {

        throw new UnsupportedOperationException(
                "Update not implemented yet"
        );
    }


    @Override
    public GoodsReceivedNoteResponse getById(Long id) {

        GoodsReceivedNote grn =
                grnRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "GRN not found with id: " + id
                                ));

        return grnMapper.toResponse(grn);
    }


    @Override
    public List<GoodsReceivedNoteResponse> getAll() {

        return grnRepository.findAll()
                .stream()
                .map(grnMapper::toResponse)
                .toList();
    }


    @Override
    @Transactional
    public void delete(Long id) {

        GoodsReceivedNote grn =
                grnRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "GRN not found with id: " + id
                                ));

        grnRepository.delete(grn);
    }
}