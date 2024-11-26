package com.citronix.exceptions;

public class ArbreNotFoundException extends RuntimeException {
    public ArbreNotFoundException(String message) {
        super(message);
    }
}