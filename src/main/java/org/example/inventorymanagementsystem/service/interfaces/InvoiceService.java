package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.request.InvoiceRequest;
import org.example.inventorymanagementsystem.dto.response.InvoiceResponse;

import java.util.List;

public interface InvoiceService {

    InvoiceResponse create(InvoiceRequest request);

    InvoiceResponse update(Long id, InvoiceRequest request);

    InvoiceResponse getById(Long id);

    List<InvoiceResponse> getAll();

    void delete(Long id);

}