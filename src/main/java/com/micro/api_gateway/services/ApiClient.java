package com.micro.api_gateway.services;

import com.micro.api_gateway.models.UserDetailsResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "auth")
public interface ApiClient {

    @PostMapping("/api/auth/validate")
    UserDetailsResponse validateToken(@RequestHeader("Authorization") String token);
}