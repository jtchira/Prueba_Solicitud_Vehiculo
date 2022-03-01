package com.ing.interview.service;

public class NotEligibleInsuranceException extends RuntimeException {

    private String message;

    public NotEligibleInsuranceException(String message) {
        super(message);
        this.message = message;
    }
    public NotEligibleInsuranceException() {
    }
}
