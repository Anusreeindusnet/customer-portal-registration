package com.example.customerportal.repository;

import com.example.customerportal.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {

    boolean existsByEmail(String email);

    Admin findByEmail(String username);

}
