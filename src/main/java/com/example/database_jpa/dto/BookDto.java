package com.example.database_jpa.dto;

import com.example.database_jpa.entities.Author;
import lombok.*;
import org.springframework.stereotype.Service;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Service

public class BookDto {

    private String title;
    private Long authorId;
}
