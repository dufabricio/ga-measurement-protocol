package com.edufabricio.lab.exception;

public class BadRequestException extends RuntimeException {

    public BadRequestException() {
    }

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message, Exception e) {
        super(message, e);
    }
}
