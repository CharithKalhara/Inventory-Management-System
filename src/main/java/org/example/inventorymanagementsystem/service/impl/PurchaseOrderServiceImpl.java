package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.PurchaseOrderItemRequest;
import org.example.inventorymanagementsystem.dto.request.PurchaseOrderRequest;
import org.example.inventorymanagementsystem.dto.response.PurchaseOrderResponse;
import org.example.inventorymanagementsystem.entity.Product;
import org.example.inventorymanagementsystem.entity.PurchaseOrder;
import org.example.inventorymanagementsystem.entity.PurchaseOrderItem;
import org.example.inventorymanagementsystem.entity.Supplier;
import org.example.inventorymanagementsystem.exception.ProductNotFoundException;
import org.example.inventorymanagementsystem.exception.PurchaseOrderAlreadyExistsException;
import org.example.inventorymanagementsystem.exception.PurchaseOrderNotFoundException;
import org.example.inventorymanagementsystem.exception.SupplierNotFoundException;
import org.example.inventorymanagementsystem.mapper.PurchaseOrderMapper;
import org.example.inventorymanagementsystem.repository.ProductRepository;
import org.example.inventorymanagementsystem.repository.PurchaseOrderRepository;
import org.example.inventorymanagementsystem.repository.SupplierRepository;
import org.example.inventorymanagementsystem.service.interfaces.PurchaseOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class PurchaseOrderServiceImpl implements PurchaseOrderService {

    private final PurchaseOrderRepository purchaseOrderRepository;
    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;
    private final PurchaseOrderMapper purchaseOrderMapper;

    @Override
    @Transactional
    public PurchaseOrderResponse create(PurchaseOrderRequest request) {

        if (purchaseOrderRepository.existsByPoNumber(request.getPoNumber())) {
            throw new PurchaseOrderAlreadyExistsException(
                    "Purchase Order already exists: " + request.getPoNumber());
        }

        Supplier supplier = supplierRepository.findById(request.getSupplierId())
                .orElseThrow(() ->
                        new SupplierNotFoundException(
                                "Supplier not found with id: " + request.getSupplierId()));

        PurchaseOrder purchaseOrder = purchaseOrderMapper.toEntity(request);
        purchaseOrder.setSupplier(supplier);
        purchaseOrder.setOrderDate(LocalDate.now());

        double totalAmount = 0;

        for (PurchaseOrderItemRequest itemRequest : request.getItems()) {

            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() ->
                            new ProductNotFoundException(
                                    "Product not found with id: " + itemRequest.getProductId()));

            PurchaseOrderItem item = purchaseOrderMapper.toItemEntity(itemRequest);

            item.setProduct(product);
            item.setPurchaseOrder(purchaseOrder);

            purchaseOrder.getItems().add(item);

            totalAmount += item.getTotalPrice();
        }

        purchaseOrder.setTotalAmount(totalAmount);

        PurchaseOrder saved = purchaseOrderRepository.save(purchaseOrder);

        return purchaseOrderMapper.toResponse(saved);
    }

    @Override
    @Transactional
    public PurchaseOrderResponse update(Long id, PurchaseOrderRequest request) {
        throw new UnsupportedOperationException("Update will be implemented next.");
    }

    @Override
    public PurchaseOrderResponse getById(Long id) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() ->
                        new PurchaseOrderNotFoundException(
                                "Purchase Order not found with id: " + id));

        return purchaseOrderMapper.toResponse(purchaseOrder);
    }

    @Override
    public java.util.List<PurchaseOrderResponse> getAll() {

        return purchaseOrderRepository.findAll()
                .stream()
                .map(purchaseOrderMapper::toResponse)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {

        PurchaseOrder purchaseOrder = purchaseOrderRepository.findById(id)
                .orElseThrow(() ->
                        new PurchaseOrderNotFoundException(
                                "Purchase Order not found with id: " + id));

        purchaseOrderRepository.delete(purchaseOrder);
    }
}