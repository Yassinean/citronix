package com.citronix.exceptions;

public class FermeNotFoundException extends RuntimeException {
    public FermeNotFoundException(String message) {
        super(message);
    }
}