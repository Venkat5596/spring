package com.example.spring_boot.controller;

import com.example.spring_boot.entities.Author;
import com.example.spring_boot.dto.AuthorRepoDto;
import com.example.spring_boot.dto.AuthorRequestDto;
import com.example.spring_boot.dto.mapper.AuthorMapper;
import com.example.spring_boot.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("/authors")
@RestController
public class AuthorController {
    private final AuthorService authorService;
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping()
    @Operation(summary = "Create new Author")
    public ResponseEntity<String> createAuthor(@RequestBody AuthorRequestDto authorRequestDto){
authorService.saveAuthor(authorRequestDto);
        return ResponseEntity.ok("ok"+" "+authorRequestDto.getName( ));
    }

    @GetMapping
    @Operation(summary = "Get all Authors")
    public ResponseEntity<List<AuthorRepoDto>> getAuthors(){
        List<Author> all = authorService.getAllAuthors();

        List<AuthorRepoDto> response = all.stream()
                .map(AuthorMapper::AuthorToDto).toList();


        return ResponseEntity.ok(response.stream().toList());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get Author by Id")
    public ResponseEntity<AuthorRepoDto> FindOne(@PathVariable("id") Long id){
        Optional<Author> foundAuthor = authorService.findOne(id);
        return foundAuthor.map(author -> ResponseEntity.ok(AuthorMapper.AuthorToDto(author))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Author by Id")
    public ResponseEntity<String> deleteAuthor(@PathVariable("id") Long id){
        authorService.deleteAuthor(id);
        return ResponseEntity.ok("Deleted");
    }
}
