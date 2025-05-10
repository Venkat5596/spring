package com.example.database_jpa.exception;


import com.example.database_jpa.exception.custom.InvalidIsbnException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalHandelException {

    @ExceptionHandler(InvalidIsbnException.class)
    public ResponseEntity<String> handleException(Exception e){
return ResponseEntity.badRequest().body(e.getMessage());
    }

}
