package com.xyzretail.exceptions;

public class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}