package org.example.inventorymanagementsystem.exception;

public class InsufficientWarehouseStockException
        extends RuntimeException {


    public InsufficientWarehouseStockException(String message) {
        super(message);
    }

}