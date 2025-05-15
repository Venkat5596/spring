package com.example.database_jpa.controller;


import com.example.database_jpa.dto.CreateBookDto;
import com.example.database_jpa.entities.Author;
import com.example.database_jpa.entities.Book;
import com.example.database_jpa.dto.BookDto;
import com.example.database_jpa.dto.mapper.Mapper;
import com.example.database_jpa.exception.custom.AuthorNotFound;
import com.example.database_jpa.repo.AuthorRepo;
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
    private final AuthorRepo authorRepo;

    private BookController(BookService bookService, Mapper<Book, BookDto> bookMapper, AuthorRepo authorRepo) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.authorRepo = authorRepo;
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
    public ResponseEntity<BookDto> createBook(@RequestBody CreateBookDto createBookDto) {
        // Map BookDto to Book entity
        Book book = bookMapper.mapToEntity(createBookDto);

        // Fetch the Author by ID from the Author repository
        Author author = authorRepo.findById(book.getAuthor().getId())
                .orElseThrow(() -> new AuthorNotFound("Author not found"));

        // Set the fetched Author to the Book entity
        book.setAuthor(author);

        // Create the book using the service
        Book savedBook = bookService.createBook(book);

        // Return the response with the saved Book as DTO
        return new ResponseEntity<>(bookMapper.mapToDto(savedBook), HttpStatus.CREATED);
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

