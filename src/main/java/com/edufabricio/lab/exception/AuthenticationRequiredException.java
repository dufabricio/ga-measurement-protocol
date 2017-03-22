package com.edufabricio.lab.exception;

public class AuthenticationRequiredException extends RuntimeException {

    public AuthenticationRequiredException() {
    }

    public AuthenticationRequiredException(String message) {
        super(message);
    }
}
