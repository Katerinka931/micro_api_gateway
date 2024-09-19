package com.micro.api_gateway.controller;

import com.micro.api_gateway.pojos.UserPojo;
import com.micro.api_gateway.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<UserPojo> login(@RequestBody UserPojo pojo) {
        return new ResponseEntity<>(authService.login(pojo), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<UserPojo> register(@RequestBody UserPojo pojo) {
        return new ResponseEntity<>(authService.register(pojo), HttpStatus.OK);
    }
}