package org.example.inventorymanagementsystem.exception;

public class InvoiceAlreadyExistsException extends RuntimeException {

    public InvoiceAlreadyExistsException(String message) {
        super(message);
    }
}