package com.example.customerportal.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TodoDto {
    private Integer id;
    private Integer userId;
    private String title;
    private boolean completed;
}