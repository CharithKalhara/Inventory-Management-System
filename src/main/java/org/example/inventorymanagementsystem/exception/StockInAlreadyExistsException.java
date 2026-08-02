package org.example.inventorymanagementsystem.exception;

public class StockInAlreadyExistsException extends RuntimeException {

    public StockInAlreadyExistsException(String message) {
        super(message);
    }
}