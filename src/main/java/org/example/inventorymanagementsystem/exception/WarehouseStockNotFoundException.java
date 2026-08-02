package org.example.inventorymanagementsystem.exception;

public class WarehouseStockNotFoundException
        extends RuntimeException {


    public WarehouseStockNotFoundException(String message) {
        super(message);
    }

}