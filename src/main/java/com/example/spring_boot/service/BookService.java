package com.example.spring_boot.service;

import com.example.spring_boot.entities.Author;
import com.example.spring_boot.entities.Book;
import com.example.spring_boot.exception.custom.InvalidIsbnException;
import com.example.spring_boot.repo.AuthorRepo;
import com.example.spring_boot.repo.BookRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class BookService {
    private final BookRepo bookRepo;
    private final AuthorRepo authorRepo;

    public BookService(BookRepo bookRepo,AuthorRepo authorRepo) {
        this.bookRepo = bookRepo;
        this.authorRepo = authorRepo;
    }


    public List<Book> getAll() {
      // return bookRepo.findAll();

       return StreamSupport.stream(bookRepo.findAll().spliterator(),false).collect(Collectors.toList());
    }

//    public Page<Book> getAll(Pageable pageable){
//        return bookRepo.findAll(pageable);
public Book createBook(Book book) {
    // Fetch the Author from the database by ID

    
    Author author = authorRepo.findById(book.getAuthor().getId())
            .orElseThrow(() -> new IllegalArgumentException("Author not found"));

    // Set the Author to the Book object
    book.setAuthor(author);

    // Save the Book to the database
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



    public boolean existsByAuthorId(Long id) {
        return bookRepo.findByAuthor_Id(id);


    }
}
