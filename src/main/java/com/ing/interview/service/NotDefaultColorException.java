package com.ing.interview.service;

public class NotDefaultColorException extends RuntimeException{

    private String message;

    public NotDefaultColorException(String message) {
        super(message);
        this.message = message;
    }
    public NotDefaultColorException() {
    }
}
