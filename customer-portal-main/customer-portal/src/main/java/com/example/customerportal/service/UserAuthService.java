package com.example.customerportal.service;

import com.example.customerportal.Roles.Role;
import com.example.customerportal.dto.LoginRequest;
import com.example.customerportal.dto.LoginResponse;
import com.example.customerportal.dto.RegistrationRequest;
import com.example.customerportal.dto.RegistrationResponse;
import com.example.customerportal.entity.Customer;
import com.example.customerportal.repository.CustomerRepository;
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
public class UserAuthService {
    private final CustomerRepository customerRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final JWTUtils jwtUtils;

    public RegistrationResponse userSignUp(RegistrationRequest signUpRequest) {

        RegistrationResponse resp = new RegistrationResponse();
        try {

            if (customerRepository.existsByEmail(signUpRequest.getEmail())) {
                resp.setHttpStatusCode(400);
                resp.setMessage("User with this email already exists.");
                return resp;
            }

            Customer customer = new Customer();
            customer.setEmail(signUpRequest.getEmail());
            customer.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
            customer.setFirstName(signUpRequest.getFirstName());
            customer.setLastName(signUpRequest.getLastName());
            customer.setPhonenumber(signUpRequest.getPhonenumber());

            if (signUpRequest != null && signUpRequest.getPassword() != null) {

                Customer ourCustomerResult = customerRepository.save(customer);
                resp.setCustomer(ourCustomerResult);
                resp.setMessage("Customer Registered Successfully.");
                resp.setHttpStatusCode(200);

            }
        } catch (Exception e) {
            resp.setHttpStatusCode(500);
            resp.setErrorMessage("Internal Server Error. Please try again.");

            e.printStackTrace();
        }
        return resp;
    }

    public ResponseEntity<LoginResponse> signInUser(LoginRequest loginRequest) {
        System.err.println("Getting in SinginUser");
        LoginResponse response = new LoginResponse();
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

            Customer customer = customerRepository.findByEmail(loginRequest.getEmail());

            var jwt = jwtUtils.generateToken(customer);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), customer);

            response.setStatus(200);
            response.setToken(jwt);
            response.setEmail(customer.getEmail());
            response.setAuthority(Role.USER);
            response.setRefreshToken(refreshToken);
            response.setTokenExpirationTime("2Hr");
            response.setLoginMessage("Successfully Signed In . Welcome " + customer.getRole());
        } catch (Exception e) {

            response.setStatus(400);
            response.setErrorMessage("username and password is incorrect ");
        }
        return ResponseEntity.ok(response);

    }}

