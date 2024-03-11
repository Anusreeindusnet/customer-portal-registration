package com.example.customerportal.dto;

import com.example.customerportal.entity.Admin;
import com.example.customerportal.entity.Customer;
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
public class RegistrationResponse {

    private Integer httpStatusCode;
    private String message;

    private Admin admin;
    private Customer customer;

    private String errorMessage;
}
