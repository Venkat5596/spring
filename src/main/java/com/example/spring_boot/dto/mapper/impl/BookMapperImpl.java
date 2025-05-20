package com.example.spring_boot.dto.mapper.impl;

import com.example.spring_boot.dto.CreateBookDto;
import com.example.spring_boot.entities.Book;
import com.example.spring_boot.dto.BookDto;
import com.example.spring_boot.dto.mapper.Mapper;
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

