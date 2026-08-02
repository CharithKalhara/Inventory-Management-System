package org.example.inventorymanagementsystem.exception;

public class StockOutNotFoundException extends RuntimeException {

    public StockOutNotFoundException(String message) {
        super(message);
    }

}