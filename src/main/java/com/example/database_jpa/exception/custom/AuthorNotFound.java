package com.example.database_jpa.exception.custom;

public class AuthorNotFound extends RuntimeException{
    public AuthorNotFound(String msg){
        super(msg);
    }
}
