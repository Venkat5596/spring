package com.example.database_jpa.exception;


import com.example.database_jpa.exception.custom.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalHandelException {

    @ExceptionHandler(InvalidIsbnException.class)
    public ResponseEntity<String> handleException(Exception e){
return ResponseEntity.badRequest().body(e.getMessage());
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity<String> handleExcception(IdNotFoundException e){
        return new ResponseEntity<String>(e.getMessage(),HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ForeginKeyException.class)
    public ResponseEntity<String> handleForeginKey(ForeginKeyException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AgeException.class)
    public ResponseEntity<String> handleAgeException(AgeException e){
        log.info("age must required");
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthorNotFound.class)
    public ResponseEntity<String> handleAuthorNotFound(AuthorNotFound a){
        log.info("Author not found log");
        return new ResponseEntity<>(a.getMessage()+" : Global",HttpStatus.BAD_REQUEST);
    }

}
