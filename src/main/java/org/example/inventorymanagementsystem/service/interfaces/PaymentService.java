package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.PaymentRequest;
import org.example.inventorymanagementsystem.dto.response.PaymentResponse;

import java.util.List;

public interface PaymentService {

    PaymentResponse create(PaymentRequest request);

    PaymentResponse update(Long id, PaymentRequest request);

    PaymentResponse getById(Long id);

    List<PaymentResponse> getAll();

    void delete(Long id);

}