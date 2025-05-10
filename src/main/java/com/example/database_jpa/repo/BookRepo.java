package com.example.database_jpa.repo;

import com.example.database_jpa.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepo extends JpaRepository<Book,String>, PagingAndSortingRepository<Book,String> {
}
