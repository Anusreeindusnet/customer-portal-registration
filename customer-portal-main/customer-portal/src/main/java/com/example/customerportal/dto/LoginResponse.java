package com.example.customerportal.dto;

import com.example.customerportal.Roles.Role;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginResponse {

    private Integer status;
    private String loginMessage;
    private String email;
    private Role authority;
    private String token;
    private String refreshToken;
    private String errorMessage;
    private String tokenExpirationTime;

}
