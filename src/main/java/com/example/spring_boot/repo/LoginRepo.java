package com.example.spring_boot.repo;

import com.example.spring_boot.entities.Login;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface LoginRepo extends JpaRepository<Login,Long> {
    Optional<Login> findByUsername(String username);
}
