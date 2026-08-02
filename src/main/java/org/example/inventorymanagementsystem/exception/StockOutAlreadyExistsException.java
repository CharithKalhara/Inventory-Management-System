package org.example.inventorymanagementsystem.exception;

public class StockOutAlreadyExistsException extends RuntimeException {

    public StockOutAlreadyExistsException(String message) {
        super(message);
    }

}