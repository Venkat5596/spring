package com.example.spring_boot.exception.custom;

public class BadCredentialsException extends Throwable {
    public BadCredentialsException(String message) {
        super(message);
    }
}
