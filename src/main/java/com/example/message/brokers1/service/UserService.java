package com.example.message.brokers1.service;

import com.example.message.brokers1.dto.CreateUserDto;
import com.example.message.brokers1.dto.UserDto;

import java.util.List;

public interface UserService {

    long save(CreateUserDto user);

    List<UserDto> getAllUsers();

    UserDto getById(Long id);

    UserDto getByUsername(String username);

    Long deleteById(Long id);

}
