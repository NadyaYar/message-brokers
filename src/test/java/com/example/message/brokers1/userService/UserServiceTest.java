package com.example.message.brokers1.userService;

import com.example.message.brokers1.dto.CreateUserDto;
import com.example.message.brokers1.dto.UserDto;
import com.example.message.brokers1.entity.User;
import com.example.message.brokers1.exceptions.UserNotFoundException;
import com.example.message.brokers1.repository.UserRepository;
import com.example.message.brokers1.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.test.context.ContextConfiguration;

import static com.example.message.brokers1.userForTest.UserForTest.userForTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ContextConfiguration(classes = {UserServiceImpl.class})
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void saveTest() {
        User expectedUser = userForTest();
        when(this.userRepository.save(any())).thenReturn(expectedUser);

        CreateUserDto createUserDto = new CreateUserDto();
        createUserDto.setLastName(expectedUser.getLastName());
        createUserDto.setAge(expectedUser.getAge());
        createUserDto.setFirstName(expectedUser.getFirstName());
        createUserDto.setUsername(expectedUser.getUsername());
        assertEquals(expectedUser.getId(), userService.save(createUserDto));
    }

    @Test
    void getUserByIdTest() {

        Optional<User> result = Optional.of(userForTest());
        when(userRepository.findById(userForTest().getId())).thenReturn(result);

        UserDto userDto = new UserDto();
        userDto.setLastName(userForTest().getLastName());
        userDto.setAge(userForTest().getAge());
        userDto.setFirstName(userForTest().getFirstName());
        when(modelMapper.map(any(), any())).thenReturn(userDto);
        assertEquals(userDto, userService.getById(userForTest().getId()));
    }

    @Test
    void getByUsernameTest() {
        Optional<User> result = Optional.of(userForTest());
        when(userRepository.findByUsername(userForTest().getUsername())).thenReturn(result);

        UserDto userDto = new UserDto();
        userDto.setLastName(userForTest().getLastName());
        userDto.setAge(userForTest().getAge());
        userDto.setFirstName(userForTest().getFirstName());
        when(modelMapper.map(any(), any())).thenReturn(userDto);
        assertEquals(userDto, userService.getByUsername(userForTest().getUsername()));
    }

    @Test
    void deleteTest() {
        Long actualUser = userService.deleteById(userForTest().getId());

        assertEquals(userForTest().getId(), actualUser);
    }

    @Test
    void userNotFoundExceptionTest() {
        assertThrows(UserNotFoundException.class, () -> userService.getById(5L));
    }
}

