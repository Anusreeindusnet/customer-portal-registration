package com.example.customerportal.service;

import com.example.customerportal.customexception.TodoNotFoundException;

import com.example.customerportal.dto.CustomerDto;
import com.example.customerportal.dto.TodoDto;
import com.example.customerportal.entity.Customer;
import com.example.customerportal.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService { // Making Http requests to an external api to fetch
    @Value("${base.url}")// Base url of external Api
    private String url;

    private final CustomerRepository customerRepository;
    private RestTemplate restTemplate = new RestTemplate(); // to make http request to the external api

    public List<CustomerDto> getAllCustomers() { // Fetching all customers converteding to customer dto objects
        List<Customer> customers = customerRepository.findAll();
        return customers.stream()
                .map(Customer::toCustomerDto) // Convert customer object to customerdto object
                .collect(Collectors.toList()); // List of Customer using collect method
    }

    public List<TodoDto> fetchAll() {

        ResponseEntity<List<TodoDto>> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<TodoDto>>() {
                    });

        } catch (HttpClientErrorException ex) { //If not found returns 404 status code and throw error message
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new TodoNotFoundException("Wrong Path ", "Please Check the url Before Typing",
                        HttpStatus.BAD_REQUEST);
            }
        }
        return responseEntity.getBody();
    }
    public TodoDto fetchById(Long todoId) {
        String apiUrl = url + "/" + todoId; //Concatenating url with parameter

        ResponseEntity<TodoDto> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    TodoDto.class);
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new TodoNotFoundException("Todo not found with ID: " + todoId,
                        "Please check the ID and try again",
                        HttpStatus.NOT_FOUND);
            }
        }

        return responseEntity.getBody();
    }
    public List<TodoDto> fetchByStatus(Boolean completed) {
        String apiUrl = url + "?completed=" + completed;

        ResponseEntity<List<TodoDto>> responseEntity = null;

        try {
            responseEntity = restTemplate.exchange(
                    apiUrl,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<List<TodoDto>>() {
                    });
        } catch (HttpClientErrorException ex) {
            if (ex.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new TodoNotFoundException("Todos not found with status: " + completed,
                        "Please check the status and try again",
                        HttpStatus.NOT_FOUND);
            }
        }

        return responseEntity.getBody();
    }

}

