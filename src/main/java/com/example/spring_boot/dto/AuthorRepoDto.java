package com.example.spring_boot.dto;


import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AuthorRepoDto {


    private String id;
    private String name;
    private String age;
}
