package com.micro.api_gateway.configs;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("forum", r -> r.path("/api/forum/**")
                        .uri("lb://discussions"))
                .route("advertisement", r -> r.path("/api/advertisement/**")
                        .uri("lb://advertisement"))
                .build();
    }
}
