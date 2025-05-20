package com.example.spring_boot.exception.custom;

public class InvalidIsbnException extends RuntimeException{
    public InvalidIsbnException(String message) {
        super(message);
    }
}
