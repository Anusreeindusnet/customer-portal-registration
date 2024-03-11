package com.example.customerportal.customexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;
@ControllerAdvice
public class GobalExceptionHandling {

    @ExceptionHandler(value = { TodoNotFoundException.class })
    public ResponseEntity<Object> handleApiRequestException(TodoNotFoundException ex) {
        APIException apiException = new APIException(ex.getMessage(), ex.ErrorDetails,
                "Please Try Again", ex.http, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }



    @ExceptionHandler(value = { CustomerNotFoundException.class })
    public ResponseEntity<Object> handleApiRequestException(CustomerNotFoundException ex) {
        APIException apiException = new APIException(ex.getMessage(), ex.ErrorDetails,
                "Please Try again", ex.http, ZonedDateTime.now(ZoneId.of("Z")));

        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {

        Map<String, String> resp = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error)->{
            String fieldName =((FieldError)error).getField();
            String message    =error.getDefaultMessage();
            resp.put(fieldName, message);
        });


        return new ResponseEntity<Map<String,String>>(resp,HttpStatus.BAD_REQUEST );
    }
}
