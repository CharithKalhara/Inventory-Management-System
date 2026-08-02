package org.example.inventorymanagementsystem.exception;

public class WarehouseAlreadyExistsException
        extends RuntimeException {


    public WarehouseAlreadyExistsException(String message) {
        super(message);
    }

}