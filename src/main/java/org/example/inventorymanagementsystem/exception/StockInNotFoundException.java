package org.example.inventorymanagementsystem.exception;

public class StockInNotFoundException extends RuntimeException {

    public StockInNotFoundException(String message) {
        super(message);
    }
}