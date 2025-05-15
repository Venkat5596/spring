package com.example.database_jpa.dto.mapper.impl;

import com.example.database_jpa.dto.CreateBookDto;
import com.example.database_jpa.entities.Book;
import com.example.database_jpa.dto.BookDto;
import com.example.database_jpa.dto.mapper.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class BookMapperImpl implements Mapper<Book, BookDto> {

    private ModelMapper modelMapper;

    public BookMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;

    }

    @Override
    public BookDto mapToDto(Book book) {

       return  modelMapper.map(book,BookDto.class);
    }

    @Override
    public Book mapToEntity(BookDto bookDto) {
        return modelMapper.map(bookDto,Book.class);
    }

    @Override
    public Book mapToEntity(CreateBookDto request) {
        return modelMapper.map(request,Book.class);
    }
}

