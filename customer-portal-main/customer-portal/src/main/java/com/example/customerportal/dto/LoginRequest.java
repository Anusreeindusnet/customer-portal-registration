package com.example.customerportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginRequest {

    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    @NotBlank(message = "Password Field Cannot be Blank")
    private String password;
}
