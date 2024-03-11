package com.example.customerportal.customexception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIException {

    private String message;

    private HttpStatus httpStatus;
    private ZonedDateTime zonedDateTime;
    private String ErrorDetails;
    private String support;

    public APIException(String message, String ErrorDetails, String support, HttpStatus httpStatus,
                        ZonedDateTime zonedDateTime) {
        this.message = message;
        this.ErrorDetails = ErrorDetails;
        this.support = support;
        this.httpStatus = httpStatus;
        this.zonedDateTime = zonedDateTime;
    }

}

