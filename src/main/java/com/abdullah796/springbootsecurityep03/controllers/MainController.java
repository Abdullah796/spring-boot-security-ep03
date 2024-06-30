package com.abdullah796.springbootsecurityep03.controllers;

import com.abdullah796.springbootsecurityep03.persistence.models.User;
import com.abdullah796.springbootsecurityep03.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
public class MainController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<String> getHomeRoute() {
        return ResponseEntity.ok("<h1>Hey welcome to home route</h1>");
    }

    @GetMapping("/users")
    public List<User> getUsers() {
        return userService.findAll();
    }

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.findById(id);
    }

}
