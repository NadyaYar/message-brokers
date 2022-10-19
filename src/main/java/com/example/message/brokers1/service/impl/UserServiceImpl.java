package com.example.message.brokers1.service.impl;

import com.example.message.brokers1.dto.CreateUserDto;
import com.example.message.brokers1.dto.UserDto;
import com.example.message.brokers1.entity.User;
import com.example.message.brokers1.exceptions.UserNotFoundException;
import com.example.message.brokers1.repository.UserRepository;
import com.example.message.brokers1.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public long save(CreateUserDto user) {
        return userRepository.save(modelMapper.map(user, User.class)).getId();
    }

    @Override
    public List<UserDto> getAllUsers() {
        ArrayList<User> arrayList = new ArrayList<>();
        userRepository.findAll().iterator().forEachRemaining(arrayList::add);
        return arrayList
                .stream()
                .map(user -> modelMapper.map(user, UserDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @SneakyThrows
    public UserDto getById(Long id) {
        return userRepository.findById(id)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new UserNotFoundException("User with  id: " + id + " not found"));
    }

    @Override
    @SneakyThrows
    public UserDto getByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(user -> modelMapper.map(user, UserDto.class))
                .orElseThrow(() -> new UserNotFoundException("User with username : " + username + " not found"));
    }

    @Override
    public Long deleteById(Long id) {
        userRepository.deleteById(id);
        return id;
    }
}
