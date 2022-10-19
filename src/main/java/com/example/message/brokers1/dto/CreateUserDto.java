package com.example.message.brokers1.dto;

import lombok.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Data
public class CreateUserDto {

    @Size(min = 2, max = 20)
    private String firstName;

    @Size(min = 2, max = 20)
    private String lastName;

    @Size(min = 2, max = 20)
    private String username;

    @Min(value = 0, message = "age cannot be less than 0")
    @Max(value = 100, message = "Max age is 100")
    private int age;
}
