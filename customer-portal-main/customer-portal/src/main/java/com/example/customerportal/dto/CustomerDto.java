package com.example.customerportal.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {
    private String statusCode;
    private String ErrorMessage;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

}
