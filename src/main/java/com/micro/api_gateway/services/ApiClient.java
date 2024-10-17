package com.micro.api_gateway.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth")
public interface ApiClient {

    @PostMapping("/api/auth/validate")
    ResponseEntity<String> validateToken(@RequestHeader("Authorization") String token);
}