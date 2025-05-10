package com.example.database_jpa.dto;


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
