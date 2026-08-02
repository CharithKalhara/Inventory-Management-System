package org.example.inventorymanagementsystem.exception;

public class PurchaseReturnAlreadyExistsException
        extends RuntimeException {

    public PurchaseReturnAlreadyExistsException(String message) {
        super(message);
    }
}