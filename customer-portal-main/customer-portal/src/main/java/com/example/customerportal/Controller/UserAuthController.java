package com.example.customerportal.Controller;

import com.example.customerportal.dto.LoginRequest;
import com.example.customerportal.dto.LoginResponse;
import com.example.customerportal.dto.RegistrationRequest;
import com.example.customerportal.dto.RegistrationResponse;
import com.example.customerportal.service.UserAuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/user")
@RequiredArgsConstructor
public class UserAuthController {

    private final UserAuthService authService;



    @PostMapping("/signup")
    public ResponseEntity<RegistrationResponse> signUp(@Valid @RequestBody RegistrationRequest signUpRequest) {

        return ResponseEntity.ok(authService.userSignUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> singin(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.signInUser(loginRequest);
    }



}
