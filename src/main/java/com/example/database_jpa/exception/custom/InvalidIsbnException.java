package com.example.database_jpa.exception.custom;

public class InvalidIsbnException extends RuntimeException{
    public InvalidIsbnException(String message) {
        super(message);
    }
}
