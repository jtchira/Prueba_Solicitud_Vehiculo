package com.ing.interview.service;

public class StockNotAvailableException extends RuntimeException{

    private String message;

    public StockNotAvailableException(String message) {
        super(message);
        this.message = message;
    }
    public StockNotAvailableException() {
    }
}
