package org.example.inventorymanagementsystem.exception;

public class SalesOrderAlreadyExistsException
        extends RuntimeException {

    public SalesOrderAlreadyExistsException(String message) {
        super(message);
    }
}