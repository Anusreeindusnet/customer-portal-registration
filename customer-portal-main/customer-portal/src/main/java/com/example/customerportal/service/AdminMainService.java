package com.example.customerportal.service;

import com.example.customerportal.Roles.Role;
import com.example.customerportal.dto.LoginRequest;
import com.example.customerportal.dto.LoginResponse;
import com.example.customerportal.dto.RegistrationRequest;
import com.example.customerportal.dto.RegistrationResponse;
import com.example.customerportal.entity.Admin;
import com.example.customerportal.repository.AdminRepository;
import com.example.customerportal.utils.JWTUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AdminMainService {
    private final AdminRepository adminRepository;

    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JWTUtils jwtUtils;

    public RegistrationResponse adminSignUp(RegistrationRequest signUpRequest) {

        RegistrationResponse resp = new RegistrationResponse();
        try {

            if (adminRepository.existsByEmail(signUpRequest.getEmail())) {
                resp.setHttpStatusCode(400);
                resp.setMessage("User with this email already exists.");
                return resp;
            }

            Admin admin = new Admin();
            admin.setEmail(signUpRequest.getEmail());
            admin.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            admin.setFirstName(signUpRequest.getFirstName());
            admin.setLastName(signUpRequest.getLastName());
            admin.setPhonenumber(signUpRequest.getPhonenumber());

            if (signUpRequest != null && signUpRequest.getPassword() != null) {




                Admin ourAdminResult = adminRepository.save(admin);
                resp.setAdmin(ourAdminResult);
                resp.setMessage("User Registered Successfully.");
                resp.setHttpStatusCode(200);



            }
        } catch (Exception e) {
            resp.setHttpStatusCode(500);
            resp.setErrorMessage("Internal Server Error. Please try again.");

            e.printStackTrace();
        }
        return resp;
    }

    public ResponseEntity<LoginResponse> signInAdmin(LoginRequest loginRequest) {

        LoginResponse response = new LoginResponse();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            Admin admin = adminRepository.findByEmail(loginRequest.getEmail());

            var jwt = jwtUtils.generateToken(admin);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), admin);

            response.setStatus(200);
            response.setToken(jwt);
            response.setAuthority(Role.ADMIN);
            response.setEmail(admin.getEmail());
            response.setRefreshToken(refreshToken);
            response.setTokenExpirationTime("2Hr");
            response.setLoginMessage("Successfully Signed In . Welcome " + admin.getRole());
        } catch (Exception e) {

            response.setStatus(400);
            response.setErrorMessage("Username and password incorrect");
        }
        return ResponseEntity.ok(response);

    }

}
