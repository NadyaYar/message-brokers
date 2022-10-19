package com.example.message.brokers1.entity;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String username;
    private int age;
}
