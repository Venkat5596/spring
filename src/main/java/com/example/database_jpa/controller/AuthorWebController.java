package com.example.database_jpa.controller;

import com.example.database_jpa.entities.Author;
import com.example.database_jpa.dto.AuthorRequestDto;
import com.example.database_jpa.service.AuthorService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/web/authors")
@RequiredArgsConstructor


// this is for web conroller data palce in jsp pages
public class AuthorWebController {

    private final AuthorService authorService;

    @GetMapping
    @Operation(summary = "Get all Authors")
    public String showAuthors(Model model) {
        List<Author> authors = authorService.getAllAuthors();
        model.addAttribute("authors", authors);
        return "authors"; // will resolve to /WEB-INF/views/authors.jsp
    }

    @PostMapping
    @Operation(summary = "Create new Author")
    public String createAuthor(@ModelAttribute("author") AuthorRequestDto authorRequestDto) {
        authorService.saveAuthor(authorRequestDto);
        return "redirect:/web/authors"; // redirects to GET view after submission
    }


}
