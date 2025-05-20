package com.example.spring_boot.controller;

import com.example.spring_boot.entities.Author;
import com.example.spring_boot.dto.AuthorRequestDto;
import com.example.spring_boot.service.AuthorService;
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
        return "authors"; // Resolves to /WEB-INF/views/authors.jsp
    }

    @PostMapping
    @Operation(summary = "Create new Author")
    public String createAuthor(@ModelAttribute AuthorRequestDto authorRequestDto) {
        if (authorRequestDto.getName() == null || authorRequestDto.getName().isEmpty()) {
            return "redirect:/web/authors?error=MissingName"; // Prevents empty names
        }
        authorService.saveAuthor(authorRequestDto);
        return "redirect:/web/authors"; // Redirect to GET view after submission
    }



}
