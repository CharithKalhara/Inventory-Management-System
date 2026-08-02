package org.example.inventorymanagementsystem.exception;

public class WarehouseNotFoundException
        extends RuntimeException {


    public WarehouseNotFoundException(String message) {
        super(message);
    }

}