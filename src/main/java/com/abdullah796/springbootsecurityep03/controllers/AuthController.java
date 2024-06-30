package com.abdullah796.springbootsecurityep03.controllers;


import com.abdullah796.springbootsecurityep03.commands.LoginCommand;
import com.abdullah796.springbootsecurityep03.dtos.LoginDto;
import com.abdullah796.springbootsecurityep03.persistence.models.User;
import com.abdullah796.springbootsecurityep03.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public User saveUser(@RequestBody User user) {
        return userService.save(user);
    }

    @PostMapping("/login")
    public LoginDto saveUser(@RequestBody LoginCommand loginCommand) {
        return userService.login(loginCommand);
    }
}
