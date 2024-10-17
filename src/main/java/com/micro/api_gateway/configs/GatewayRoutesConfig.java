package com.micro.api_gateway.configs;

import com.micro.api_gateway.filters.AuthFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayRoutesConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder, AuthFilter authFilter) {
        return builder.routes()
                .route("auth", r -> r.path("/api/auth/**")
                        .uri("lb://auth"))
                .route("forum", r -> r.path("/api/forum/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthFilter.Config())))
                        .uri("lb://discussions"))
                .route("advertisement", r -> r.path("/api/advertisement/**")
                        .filters(f -> f.filter(authFilter.apply(new AuthFilter.Config())))
                        .uri("lb://advertisement"))
                .build();
    }
}
