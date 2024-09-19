package com.micro.api_gateway.service;

import com.micro.api_gateway.entity.User;
import com.micro.api_gateway.pojos.UserPojo;
import com.micro.api_gateway.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    public UserPojo register(UserPojo pojo) {
        User user = UserPojo.toEntity(pojo);
        user.setPassword(passwordEncoder.encode(pojo.getPassword()));
        return UserPojo.fromEntity(userRepository.save(user));
    }

    public UserPojo login(UserPojo pojo) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        pojo.getUsername(),
                        pojo.getPassword())
        );
        User user = userRepository.findUserByUsername(pojo.getUsername()).orElseThrow(() -> {
            throw new NoSuchElementException();
        });
        return UserPojo.fromEntity(user);
    }
}
