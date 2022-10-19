package com.example.message.brokers1.userForTest;

import com.example.message.brokers1.entity.User;

public class UserForTest {
    public static User userForTest() {
        User user = new User();
        user.setLastName("Something");
        user.setId(1L);
        user.setAge(18);
        user.setFirstName("Kate");
        user.setUsername("user1");
        return user;
    }
}
