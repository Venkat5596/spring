package com.example.spring_boot.dto;

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

    private AuthorRepoDto authorRepoDto;
}
