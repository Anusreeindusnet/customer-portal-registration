package com.example.customerportal.Controller;

import com.example.customerportal.customexception.CustomerNotFoundException;
import com.example.customerportal.dto.CustomerDto;
import com.example.customerportal.dto.RegistrationRequest;
import com.example.customerportal.dto.RegistrationResponse;
import com.example.customerportal.service.AdminService;
import com.example.customerportal.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final CustomerService customerService;
    private final AdminService adminService;

    @GetMapping("/show-all-customer")
    public List<CustomerDto> getAllCustomers() {
        List<CustomerDto> custom=customerService.getAllCustomers();

        if(custom!=null && custom.size()>0)
            return custom;

        throw new CustomerNotFoundException("Unable to Get Customers", "No Customer Found", HttpStatus.NOT_FOUND);

    }

    @PostMapping("/addAdmin")
    public ResponseEntity<RegistrationResponse> addAdmin(@Valid  @RequestBody RegistrationRequest registrationRequest) {
        return adminService.addAdmin(registrationRequest);

    }

}

