package com.citronix.exceptions;

public class RecolteNotFoundException extends RuntimeException {
    public RecolteNotFoundException(String message) {
        super(message);
    }
}