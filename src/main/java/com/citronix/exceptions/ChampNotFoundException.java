package com.citronix.exceptions;

public class ChampNotFoundException extends RuntimeException {
    public ChampNotFoundException(String message) {
        super(message);
    }
}