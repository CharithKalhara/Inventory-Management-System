package org.example.inventorymanagementsystem.exception;

public class StockTransferNotFoundException
        extends RuntimeException {


    public StockTransferNotFoundException(String message) {
        super(message);
    }

}