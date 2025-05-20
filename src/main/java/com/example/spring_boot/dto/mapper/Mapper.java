package com.example.spring_boot.dto.mapper;


import com.example.spring_boot.dto.CreateBookDto;
import com.example.spring_boot.entities.Book;

public interface Mapper<A,B>{

    B mapToDto(A a);
    A mapToEntity(B b);

    Book mapToEntity(CreateBookDto request);
}
