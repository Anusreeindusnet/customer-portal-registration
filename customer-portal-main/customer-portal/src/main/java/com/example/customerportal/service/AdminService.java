package com.example.customerportal.service;

import com.example.customerportal.Roles.Role;
import com.example.customerportal.dto.RegistrationRequest;
import com.example.customerportal.dto.RegistrationResponse;
import com.example.customerportal.entity.Admin;
import com.example.customerportal.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminService {
    private final BCryptPasswordEncoder passwordEncoder;
    private final AdminRepository adminRepository;

    public ResponseEntity<RegistrationResponse> addAdmin(RegistrationRequest registrationRequest) {
        RegistrationResponse response = new RegistrationResponse();
        Admin admin = new Admin();
        admin.setEmail(registrationRequest.getEmail());
        admin.setFirstName(registrationRequest.getFirstName());
        admin.setLastName(registrationRequest.getLastName());
        admin.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));
        admin.setPhonenumber(registrationRequest.getPhonenumber());
        admin.setRole(Role.ADMIN);
        admin.setIsverifies(true);

        adminRepository.save(admin);
        response.setAdmin(admin);
        response.setMessage("Admin Added Successfully");
        response.setHttpStatusCode(200);

        return ResponseEntity.ok(response);

    }

}

