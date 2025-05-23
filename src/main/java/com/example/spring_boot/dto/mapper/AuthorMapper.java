package com.example.spring_boot.dto.mapper;

import com.example.spring_boot.dto.AuthorRepoDto;


import com.example.spring_boot.entities.Author;
import com.example.spring_boot.dto.AuthorRequestDto;

public class AuthorMapper {
    public static AuthorRepoDto AuthorToDto(Author author){
        AuthorRepoDto authorRepo = new AuthorRepoDto();
        authorRepo.setId(author.getId().toString());
        authorRepo.setAge(String.valueOf(author.getAge()));
        authorRepo.setName(author.getName());
        return authorRepo;

    }

    public static Author DtoToAuthor(AuthorRequestDto authorRepo){
        Author author = new Author();
        if (authorRepo.getName() != null && !authorRepo.getName().isBlank()) {
           // author.setId(Long.valueOf(authorRepo.getId()));
        }
        author.setAge(Integer.valueOf(authorRepo.getAge()));
        author.setName(authorRepo.getName());
        return author;
    }

}
