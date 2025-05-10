package com.example.database_jpa.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorRequestDto {

    @NotBlank
    private String id;


    private String name;
    private String age;
}
