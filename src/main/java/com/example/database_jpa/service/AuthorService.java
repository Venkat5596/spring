package com.example.database_jpa.service;

import com.example.database_jpa.entities.Author;
import com.example.database_jpa.dto.AuthorRequestDto;
import com.example.database_jpa.dto.mapper.AuthorMapper;
import com.example.database_jpa.repo.AuthorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorService {
    private final AuthorRepo authorRepo;
    public AuthorService(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    public Iterable<Author> ageLessThan(int age){
        return authorRepo.ageLessThan(age);
    }

    public Author saveAuthor(AuthorRequestDto authorRequestDto) {
        return authorRepo.save(AuthorMapper.DtoToAuthor(authorRequestDto));
    }
        public List<Author> getAllAuthors(){
        List<Author> all = authorRepo.findAll();
        return all;
        }

    public Optional<Author> findOne(Long id) {
        return authorRepo.findById(Long.valueOf(id));
    }
}
