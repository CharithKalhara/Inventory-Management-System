package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.request.PaymentRequest;
import org.example.inventorymanagementsystem.dto.response.PaymentResponse;
import org.example.inventorymanagementsystem.entity.Invoice;
import org.example.inventorymanagementsystem.entity.Payment;
import org.example.inventorymanagementsystem.exception.InvoiceNotFoundException;
import org.example.inventorymanagementsystem.exception.PaymentAlreadyExistsException;
import org.example.inventorymanagementsystem.exception.PaymentNotFoundException;
import org.example.inventorymanagementsystem.mapper.PaymentMapper;
import org.example.inventorymanagementsystem.repository.InvoiceRepository;
import org.example.inventorymanagementsystem.repository.PaymentRepository;
import org.example.inventorymanagementsystem.service.interfaces.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final InvoiceRepository invoiceRepository;
    private final PaymentMapper paymentMapper;


    @Override
    @Transactional
    public PaymentResponse create(PaymentRequest request) {

        if (paymentRepository.existsByPaymentNumber(
                request.getPaymentNumber())) {

            throw new PaymentAlreadyExistsException(
                    "Payment already exists: "
                            + request.getPaymentNumber());
        }


        Invoice invoice =
                invoiceRepository.findById(
                        request.getInvoiceId()
                ).orElseThrow(() ->
                        new InvoiceNotFoundException(
                                "Invoice not found with id: "
                                        + request.getInvoiceId()
                        ));


        Payment payment =
                paymentMapper.toEntity(request);


        payment.setInvoice(invoice);
        payment.setPaymentDate(LocalDate.now());


        // Update invoice status
        if (request.getAmount() >= invoice.getTotalAmount()) {

            invoice.setStatus("PAID");

        } else {

            invoice.setStatus("PARTIALLY_PAID");
        }


        invoiceRepository.save(invoice);


        Payment saved =
                paymentRepository.save(payment);


        return paymentMapper.toResponse(saved);
    }


    @Override
    public PaymentResponse update(Long id,
                                  PaymentRequest request) {

        Payment payment =
                paymentRepository.findById(id)
                        .orElseThrow(() ->
                                new PaymentNotFoundException(
                                        "Payment not found with id: "
                                                + id));


        paymentMapper.updateEntity(payment, request);


        Payment updated =
                paymentRepository.save(payment);


        return paymentMapper.toResponse(updated);
    }


    @Override
    public PaymentResponse getById(Long id) {

        Payment payment =
                paymentRepository.findById(id)
                        .orElseThrow(() ->
                                new PaymentNotFoundException(
                                        "Payment not found with id: "
                                                + id));


        return paymentMapper.toResponse(payment);
    }


    @Override
    public List<PaymentResponse> getAll() {

        return paymentRepository.findAll()
                .stream()
                .map(paymentMapper::toResponse)
                .toList();
    }


    @Override
    @Transactional
    public void delete(Long id) {

        Payment payment =
                paymentRepository.findById(id)
                        .orElseThrow(() ->
                                new PaymentNotFoundException(
                                        "Payment not found with id: "
                                                + id));


        paymentRepository.delete(payment);
    }
}