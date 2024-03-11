package com.example.customerportal.repository;

import com.example.customerportal.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    boolean existsByEmail(String email);

    Customer findByEmail(String email);

}
