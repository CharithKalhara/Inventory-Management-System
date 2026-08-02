package org.example.inventorymanagementsystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.inventorymanagementsystem.dto.response.DashboardResponse;
import org.example.inventorymanagementsystem.dto.response.RecentInvoiceResponse;
import org.example.inventorymanagementsystem.entity.Invoice;
import org.example.inventorymanagementsystem.repository.*;
import org.example.inventorymanagementsystem.service.interfaces.DashboardService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl
        implements DashboardService {


    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;

    private final SupplierRepository supplierRepository;

    private final WarehouseRepository warehouseRepository;

    private final StockRepository stockRepository;

    private final InvoiceRepository invoiceRepository;


    @Override
    public DashboardResponse getDashboard() {


        BigDecimal stockValue =
                stockRepository.findAll()
                        .stream()
                        .map(stock ->
                                stock.getProduct()
                                        .getPurchasePrice()
                                        .multiply(
                                                BigDecimal.valueOf(
                                                        stock.getQuantity()
                                                )
                                        )
                        )
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );


        Long lowStockCount =
                stockRepository.findAll()
                        .stream()
                        .filter(stock ->
                                stock.getQuantity()
                                        <=
                                        stock.getProduct()
                                                .getMinimumStock()
                        )
                        .count();


        List<RecentInvoiceResponse> invoices =
                invoiceRepository
                        .findTop5ByOrderByInvoiceDateDesc()
                        .stream()
                        .map(this::mapInvoice)
                        .toList();


        return DashboardResponse.builder()

                .totalProducts(
                        productRepository.count()
                )

                .totalCustomers(
                        customerRepository.count()
                )

                .totalSuppliers(
                        supplierRepository.count()
                )

                .totalWarehouses(
                        warehouseRepository.count()
                )

                .stockValue(
                        stockValue
                )

                .lowStockProducts(
                        lowStockCount
                )

                .recentInvoices(
                        invoices
                )

                .build();
    }


    private RecentInvoiceResponse mapInvoice(Invoice invoice) {

        return RecentInvoiceResponse.builder()

                .invoiceNumber(
                        invoice.getInvoiceNumber()
                )

                .customerName(
                        invoice.getSalesOrder()
                                .getCustomer()
                                .getName()
                )

                .invoiceDate(
                        invoice.getInvoiceDate()
                )

                .totalAmount(
                        invoice.getTotalAmount()
                )

                .status(
                        invoice.getStatus()
                )

                .build();
    }

}