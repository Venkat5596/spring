package com.example.database_jpa.controller;


import com.example.database_jpa.dto.AuthorRepoDto;
import com.example.database_jpa.dto.BookDto;
import com.example.database_jpa.dto.mapper.Mapper;
import com.example.database_jpa.dto.mapper.impl.BookMapperImpl;
import com.example.database_jpa.entities.Author;
import com.example.database_jpa.entities.Book;
import com.example.database_jpa.repo.AuthorRepo;
import com.example.database_jpa.repo.BookRepo;
import com.example.database_jpa.service.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import static org.apache.logging.log4j.util.LambdaUtil.getAll;


@Controller
@RequestMapping("/web/books")
public class BookWebController {

    private final BookService bookService;
    private final Mapper<Book,BookDto> bookMapper;
    private AuthorRepo authorRepo;

    public BookWebController(BookService bookService, Mapper<Book,BookDto> bookMapper,AuthorRepo authorRepo) {
        this.bookService = bookService;
        this.bookMapper = bookMapper;
        this.authorRepo = authorRepo;

    }


    @GetMapping
    public String showBooks(Model model){
       List<Book> books = bookService.getAll();
       model.addAttribute("books",books);
        return "books";
    }

    @PostMapping
    public String createBook(@ModelAttribute("book") BookDto bookDto){
        Book book = bookMapper.mapToEntity(bookDto);

        // Fetch the Author by ID from the Author repository
        Author author = authorRepo.findById(bookDto.getAuthorId())
                .orElseThrow(() -> new IllegalArgumentException("Author not found"));

        // Set the fetched Author to the Book entity
        book.setAuthor(author);

        // Create the book using the service
        Book savedBook = bookService.createBook(book);

        return "redirect:/web/books";
    }
}
