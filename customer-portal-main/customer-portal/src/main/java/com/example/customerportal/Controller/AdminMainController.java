package com.example.customerportal.Controller;

import com.example.customerportal.dto.LoginRequest;
import com.example.customerportal.dto.LoginResponse;
import com.example.customerportal.dto.RegistrationRequest;
import com.example.customerportal.dto.RegistrationResponse;
import com.example.customerportal.service.AdminMainService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/admin")
@RequiredArgsConstructor
public class AdminMainController {

    private final AdminMainService authService;



    @PostMapping("/signup")
    public ResponseEntity<RegistrationResponse> signUp(@Valid @RequestBody RegistrationRequest signUpRequest) {
        System.err.println(signUpRequest.toString());
        return ResponseEntity.ok(authService.adminSignUp(signUpRequest));
    }

    @PostMapping("/signin")
    public ResponseEntity<LoginResponse> singin(@Valid @RequestBody LoginRequest loginRequest) {
        return authService.signInAdmin(loginRequest);
    }


}