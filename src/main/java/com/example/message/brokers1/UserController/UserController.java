package com.example.message.brokers1.UserController;

import com.example.message.brokers1.dto.CreateUserDto;
import com.example.message.brokers1.dto.UserDto;
import com.example.message.brokers1.service.impl.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl userService;

    @PostMapping("/save")
    @ResponseStatus(HttpStatus.CREATED)
    public Long saveUser(@Validated @RequestBody CreateUserDto user) {
        return userService.save(user);
    }

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/findById/{id}")
    public UserDto getUserByUserId(@PathVariable Long id) {
        return userService.getById(id);
    }

    @GetMapping("/findByUsername/{username}")
    public UserDto getUserByUsername(@RequestParam("username") String username) {
        return userService.getByUsername(username);
    }

    @DeleteMapping("/deleteById/{id}")
    public Long deleteUserById(Long id){
        return userService.deleteById(id);
    }
}
