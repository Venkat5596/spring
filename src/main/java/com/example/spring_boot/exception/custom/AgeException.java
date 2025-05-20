package com.example.spring_boot.exception.custom;

public class AgeException extends RuntimeException{
    public AgeException(String msg){
        super(msg);
    }
}
