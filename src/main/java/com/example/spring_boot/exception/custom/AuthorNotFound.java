package com.example.spring_boot.exception.custom;

public class AuthorNotFound extends RuntimeException{
    public AuthorNotFound(String msg){
        super(msg);
    }
}
