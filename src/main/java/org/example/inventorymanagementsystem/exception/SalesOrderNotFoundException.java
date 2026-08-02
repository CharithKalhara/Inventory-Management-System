package org.example.inventorymanagementsystem.exception;

public class SalesOrderNotFoundException
        extends RuntimeException {

    public SalesOrderNotFoundException(String message) {
        super(message);
    }
}