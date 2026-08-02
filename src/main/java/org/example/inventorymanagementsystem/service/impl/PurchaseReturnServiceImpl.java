package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.PurchaseReturnItemRequest;
import org.example.inventorymanagementsystem.dto.request.PurchaseReturnRequest;
import org.example.inventorymanagementsystem.dto.response.PurchaseReturnResponse;
import org.example.inventorymanagementsystem.entity.*;
import org.example.inventorymanagementsystem.mapper.PurchaseReturnMapper;
import org.example.inventorymanagementsystem.repository.*;
import org.example.inventorymanagementsystem.service.interfaces.PurchaseReturnService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PurchaseReturnServiceImpl
        implements PurchaseReturnService {


    private final PurchaseReturnRepository purchaseReturnRepository;
    private final GoodsReceivedNoteRepository goodsReceivedNoteRepository;
    private final ProductRepository productRepository;
    private final StockRepository stockRepository;
    private final PurchaseReturnMapper purchaseReturnMapper;


    @Override
    @Transactional
    public PurchaseReturnResponse create(
            PurchaseReturnRequest request) {


        if (purchaseReturnRepository
                .existsByReturnNumber(request.getReturnNumber())) {

            throw new RuntimeException(
                    "Purchase Return already exists: "
                            + request.getReturnNumber()
            );
        }


        GoodsReceivedNote grn =
                goodsReceivedNoteRepository.findById(
                        request.getGrnId()
                ).orElseThrow(() ->
                        new RuntimeException(
                                "GRN not found"
                        ));


        PurchaseReturn purchaseReturn =
                purchaseReturnMapper.toEntity(request);


        purchaseReturn.setGoodsReceivedNote(grn);
        purchaseReturn.setReturnDate(LocalDate.now());


        double totalAmount = 0;


        for (PurchaseReturnItemRequest itemRequest :
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


            // Reduce stock after returning to supplier

            if (stock.getQuantity()
                    < itemRequest.getQuantity()) {

                throw new RuntimeException(
                        "Insufficient stock for "
                                + product.getName()
                );
            }


            stock.setQuantity(
                    stock.getQuantity()
                            - itemRequest.getQuantity()
            );


            stock.setAvailableQuantity(
                    stock.getQuantity()
                            - stock.getReservedQuantity()
            );


            stockRepository.save(stock);



            PurchaseReturnItem item =
                    purchaseReturnMapper
                            .toItemEntity(itemRequest);


            item.setProduct(product);
            item.setPurchaseReturn(purchaseReturn);


            purchaseReturn.getItems()
                    .add(item);


            totalAmount += item.getTotalPrice();
        }


        purchaseReturn.setTotalAmount(totalAmount);


        PurchaseReturn saved =
                purchaseReturnRepository.save(
                        purchaseReturn
                );


        return purchaseReturnMapper.toResponse(saved);
    }


    @Override
    public PurchaseReturnResponse update(
            Long id,
            PurchaseReturnRequest request) {

        throw new UnsupportedOperationException(
                "Update not implemented yet"
        );
    }


    @Override
    public PurchaseReturnResponse getById(Long id) {

        PurchaseReturn purchaseReturn =
                purchaseReturnRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Purchase Return not found"
                                ));

        return purchaseReturnMapper.toResponse(
                purchaseReturn
        );
    }


    @Override
    public List<PurchaseReturnResponse> getAll() {

        return purchaseReturnRepository.findAll()
                .stream()
                .map(purchaseReturnMapper::toResponse)
                .toList();
    }


    @Override
    @Transactional
    public void delete(Long id) {

        PurchaseReturn purchaseReturn =
                purchaseReturnRepository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Purchase Return not found"
                                ));

        purchaseReturnRepository.delete(
                purchaseReturn
        );
    }

}