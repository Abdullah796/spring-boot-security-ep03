package com.abdullah796.springbootsecurityep03.services;


import com.abdullah796.springbootsecurityep03.commands.LoginCommand;
import com.abdullah796.springbootsecurityep03.dtos.LoginDto;
import com.abdullah796.springbootsecurityep03.persistence.models.User;
import com.abdullah796.springbootsecurityep03.persistence.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public User save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public LoginDto login(LoginCommand loginCommand) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginCommand.getUsername(), loginCommand.getPassword())
        );

        User user = userRepository.findByUsername(loginCommand.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User with username: " + loginCommand.getUsername() + " not found !"));

        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach((role) -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });

        UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);

        return LoginDto
                .builder()
                .jwt(jwtService.generateToken(userDetails))
                .isLoggedIn(Boolean.TRUE)
                .build();
    }

}
