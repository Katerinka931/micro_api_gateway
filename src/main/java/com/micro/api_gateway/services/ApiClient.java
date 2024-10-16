package com.micro.api_gateway.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "auth")
public interface ApiClient {

    @GetMapping("/api/auth/validate_token")
    ResponseEntity<String> validateToken(@RequestParam("token") String token);
}
