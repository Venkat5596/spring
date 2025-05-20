package com.example.spring_boot.exception.custom;

public class ForeginKeyException extends RuntimeException{
    public ForeginKeyException(String message) {
        super(message);
    }
}
