package com.abdullah796.springbootsecurityep03.persistence.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "user_name", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "roles", nullable = false)
    @ElementCollection
    private List<String> roles;

    @Column(name = "active", nullable = false)
    private Boolean active;
}
