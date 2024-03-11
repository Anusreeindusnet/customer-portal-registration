package com.example.customerportal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationRequest {
    private Long id;
    @NotBlank(message = "First Name Cannot be blank")
    @NotNull(message = "First Name Cannot be Null")
    private String firstName;

    @NotBlank(message = "Last Name Cannot be blank")
    @NotNull(message = "Last Name Cannot be Null")
    private String lastName;

    @Email(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;

    @Size(min = 8, message = "Password must be between 8 and 16 characters")
    private String password;

    @NotNull(message = "Please Enter Phone Number It cannot be null")
    @Size(min = 10, max = 10, message = "Phone Number must be 10 digits")
    private String phonenumber;
}
