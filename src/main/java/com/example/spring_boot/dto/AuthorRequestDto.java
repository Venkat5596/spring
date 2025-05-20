package com.example.spring_boot.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorRequestDto {

//    @NotBlank
//    private String id;

    @NotBlank(message = "name must required")
    private String name;
    @Min(10)
    @Max(100)
    private String age;
}
