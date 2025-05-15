package com.example.database_jpa.exception.custom;

public class ForeginKeyException extends RuntimeException{
    public ForeginKeyException(String message) {
        super(message);
    }
}
