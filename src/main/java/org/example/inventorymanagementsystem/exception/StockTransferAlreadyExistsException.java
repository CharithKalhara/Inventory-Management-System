package org.example.inventorymanagementsystem.exception;

public class StockTransferAlreadyExistsException
        extends RuntimeException {


    public StockTransferAlreadyExistsException(String message) {
        super(message);
    }

}