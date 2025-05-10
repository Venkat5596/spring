package com.example.database_jpa.controller;


import com.example.database_jpa.entities.Book;
import com.example.database_jpa.dto.BookDto;
import com.example.database_jpa.dto.mapper.Mapper;
import com.example.database_jpa.service.BookService;
//import org.springframework.beans.factory.annotation.Autowired;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/books")
@RestController
public class BookController {

    private final BookService bookService;


    private final Mapper<Book, BookDto> bookMapper;

    private BookController(BookService bookService, Mapper<Book, BookDto> bookMapper) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
    }

    @GetMapping
    public List<ResponseEntity<BookDto>> getBooks() {
        List<Book> all = bookService.getAll();
        List<BookDto> books = all.stream()
                .map(bookMapper::mapToDto).toList();

        return books.stream().map(ResponseEntity::ok).toList();
    }

//    @GetMapping
//    @Operation
//    public Page<BookDto> getBooks(Pageable pageable) {
//        Page<Book> all = bookService.getAll(pageable);
//        Page<BookDto> books = all.map(bookMapper::mapToDto);
//        return books;
//
//    }

    @PostMapping
    @Operation(summary = "Create new Book")
    public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToEntity(bookDto);

        Book book1 = bookService.createBook(book);
        return new ResponseEntity<BookDto>(bookMapper.mapToDto(book1), HttpStatus.CREATED);
    }


    @PutMapping("/{isbn}")
    @Operation(summary = "Update Book")
    public ResponseEntity<BookDto> updateBook(@PathVariable("isbn") String isbn, @RequestBody BookDto bookDto) {
        Book book = bookMapper.mapToEntity(bookDto);
        Book save = bookService.updateBook(isbn,book);
        BookDto bookDto1 = bookMapper.mapToDto(save);

        return new ResponseEntity<BookDto>(bookDto1,HttpStatus.CREATED);
    }
    @DeleteMapping("/{isbn}")
    @Operation(summary = "Delete Book")
    public ResponseEntity<String> deleteBook(@PathVariable("isbn") String isbn){
        bookService.deleteBook(isbn);
        return ResponseEntity.ok("Deleted");
    }
    @PatchMapping("/isbn/{isbn}" )
    @Operation(summary = "Patch Book")
    public ResponseEntity<BookDto> patchBook(@PathVariable String isbn ,@RequestBody BookDto bookDto){
        Book book = bookMapper.mapToEntity(bookDto);
        Book save = bookService.patchBook(isbn,book);
        BookDto bookDto1 = bookMapper.mapToDto(save);
        return ResponseEntity.ok(bookDto1);
    }
}

