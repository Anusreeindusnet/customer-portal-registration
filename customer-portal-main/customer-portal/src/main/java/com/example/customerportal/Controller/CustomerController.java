package com.example.customerportal.Controller;

import com.example.customerportal.customexception.TodoNotFoundException;
import com.example.customerportal.dto.TodoDto;
import com.example.customerportal.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/show-all-todos")
    public List<TodoDto> getPosts() {
        return customerService.fetchAll();
    }
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<TodoDto> fetchById(@PathVariable String id) {
        try {
            Integer i = Integer.parseInt(id);
            System.err.println(i);

        } catch (Exception e) {
            throw new TodoNotFoundException("Id MisMatch Exception", "Please check the id format its not correct",
                    HttpStatus.NOT_ACCEPTABLE);
        }
        TodoDto todo = customerService.fetchById(Long.parseLong(id));
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }
    @GetMapping("/status/{completed}")
    public ResponseEntity<List<TodoDto>> fetchByStatus(@PathVariable Boolean completed) {
        List<TodoDto> todos = customerService.fetchByStatus(completed);
        return new ResponseEntity<>(todos, HttpStatus.OK);
    }

}

