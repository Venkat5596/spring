package com.example.database_jpa.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Getter
@Setter
public class CreateBookDto
{

    @NotBlank
    private String title;

    @NotNull
    private Long authorId;

}
