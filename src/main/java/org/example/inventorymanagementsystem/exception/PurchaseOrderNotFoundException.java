package org.example.inventorymanagementsystem.exception;

public class PurchaseOrderNotFoundException extends RuntimeException {

    public PurchaseOrderNotFoundException(String message) {
        super(message);
    }
}