package com.example.database_jpa.exception.custom;

public class BadCredentialsException extends Throwable {
    public BadCredentialsException(String message) {
        super(message);
    }
}
