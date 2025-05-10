package com.example.database_jpa.controller;

import com.example.database_jpa.entities.Author;
import com.example.database_jpa.dto.AuthorRepoDto;
import com.example.database_jpa.dto.AuthorRequestDto;
import com.example.database_jpa.dto.mapper.AuthorMapper;
import com.example.database_jpa.service.AuthorService;
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

    @GetMapping("/id")
    @Operation(summary = "Get Author by Id")
    public ResponseEntity<AuthorRepoDto> FindOne(@PathVariable("id") Long id){
        Optional<Author> foundAuthor = authorService.findOne(id);
        return foundAuthor.map(author -> ResponseEntity.ok(AuthorMapper.AuthorToDto(author))).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
