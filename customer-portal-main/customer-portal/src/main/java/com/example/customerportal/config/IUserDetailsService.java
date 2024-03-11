package com.example.customerportal.config;

import com.example.customerportal.entity.Customer;
import com.example.customerportal.repository.AdminRepository;
import com.example.customerportal.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class IUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        Customer customer = customerRepository.findByEmail(username);
        if (customer != null)
            return customer;
        else
            return adminRepository.findByEmail(username);

    }

}
