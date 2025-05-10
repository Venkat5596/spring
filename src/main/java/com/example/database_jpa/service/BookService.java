package com.example.database_jpa.service;

import com.example.database_jpa.entities.Book;
import com.example.database_jpa.exception.custom.InvalidIsbnException;
import com.example.database_jpa.repo.BookRepo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    private final BookRepo bookRepo;

    public BookService(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }


    public List<Book> getAll() {
      // return bookRepo.findAll();

       return StreamSupport.stream(bookRepo.findAll().spliterator(),false).collect(Collectors.toList());
    }

//    public Page<Book> getAll(Pageable pageable){
//        return bookRepo.findAll(pageable);
//    }
    public Book createBook(Book book) {
        return bookRepo.save(book);
    }

    public Book updateBook(String isbn, Book book) {
        if(book.getIsbn().equals(isbn)){
            book.setIsbn(isbn);
            return bookRepo.save(book);}
        else{
            throw new InvalidIsbnException("invalid isbn");

        }

    }

    public void deleteBook(String isbn) {
        if(bookRepo.existsById(isbn)){
            bookRepo.deleteById(isbn);
        }
        else{
            throw new InvalidIsbnException("invalid isbn");
        }
    }

    public Book patchBook(String isbn, Book book) {
        return bookRepo.findById(isbn).map(existingBook -> {
            if (book.getTitle() != null) existingBook.setTitle(book.getTitle());
            if (book.getAuthor() != null) existingBook.setAuthor(book.getAuthor());
            // Add other fields here...
            return bookRepo.save(existingBook);
        }).orElseThrow(() -> new InvalidIsbnException("invalid isbn"));
    }

}
