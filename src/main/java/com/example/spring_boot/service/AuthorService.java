package com.example.spring_boot.service;

import com.example.spring_boot.entities.Author;
import com.example.spring_boot.dto.AuthorRequestDto;
import com.example.spring_boot.dto.mapper.AuthorMapper;
import com.example.spring_boot.exception.custom.AgeException;
import com.example.spring_boot.exception.custom.ForeginKeyException;
import com.example.spring_boot.exception.custom.IdNotFoundException;
import com.example.spring_boot.repo.AuthorRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class AuthorService {
    private final AuthorRepo authorRepo;
    private final BookService bookService;

    public AuthorService(AuthorRepo authorRepo, BookService bookService) {
        this.authorRepo = authorRepo;
        this.bookService = bookService;
    }

    public Iterable<Author> ageLessThan(int age){
        return authorRepo.ageLessThan(age);
    }

    public Author saveAuthor(AuthorRequestDto authorRequestDto) {
        if(Integer.parseInt(authorRequestDto.getAge())<=0){
            throw new AgeException("Age must required");
        }
        return authorRepo.save(AuthorMapper.DtoToAuthor(authorRequestDto));
    }
        public List<Author> getAllAuthors(){
        List<Author> all = authorRepo.findAll();
        return all;
        }

    public Optional<Author> findOne(Long id) {
        return Optional.ofNullable(authorRepo.findById(Long.valueOf(id)).orElseThrow(() -> new IdNotFoundException("Id not found: " + id + " ")));
    }
    public void deleteAuthor(Long id) {
    if(bookService.existsByAuthorId(id)){
        throw new ForeginKeyException("counld not delete author with id: " + id + " because it has books in the database.");
    }
        authorRepo.findById(id).orElseThrow(() -> new IdNotFoundException("Id not found: " + id));
        authorRepo.deleteById(id);
    }
}
