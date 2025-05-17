package com.example.database_jpa.jwt.login;

import com.example.database_jpa.entities.Login;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponseDto {
    private String token;
    private Long id;
    private String username;
    private String role;

//    public Login getLogin() {
//        return new Login();
//    }


}