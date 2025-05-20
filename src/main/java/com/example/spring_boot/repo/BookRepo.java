package com.example.spring_boot.repo;

import com.example.spring_boot.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book,String>, PagingAndSortingRepository<Book,String> {
    boolean findByAuthor_Id(Long authorId);
}
