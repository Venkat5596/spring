package com.example.database_jpa.dto.mapper;


import com.example.database_jpa.dto.CreateBookDto;
import com.example.database_jpa.entities.Book;

public interface Mapper<A,B>{

    B mapToDto(A a);
    A mapToEntity(B b);

    Book mapToEntity(CreateBookDto request);
}
