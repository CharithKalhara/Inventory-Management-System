package org.example.inventorymanagementsystem.exception;

public class PurchaseOrderAlreadyExistsException extends RuntimeException {

    public PurchaseOrderAlreadyExistsException(String message) {
        super(message);
    }
}