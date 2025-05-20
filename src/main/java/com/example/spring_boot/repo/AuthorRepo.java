package com.example.spring_boot.repo;

import com.example.spring_boot.entities.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepo extends JpaRepository<Author,Long> {
    Iterable<Author> ageLessThan(int age);


   // Optional<Author> findById(String id);
}
