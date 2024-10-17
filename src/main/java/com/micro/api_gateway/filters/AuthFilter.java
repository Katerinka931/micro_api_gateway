package com.micro.api_gateway.filters;

import com.micro.api_gateway.services.ApiClient;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.GatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.ObjectProvider;

@Component
public class AuthFilter implements GatewayFilterFactory<AuthFilter.Config> {

    private final ObjectProvider<ApiClient> apiClientProvider;

    public AuthFilter(ObjectProvider<ApiClient> apiClientProvider) {
        this.apiClientProvider = apiClientProvider;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            ApiClient apiClient = apiClientProvider.getObject();
            String token = exchange.getRequest().getHeaders().getFirst("Authorization");

            try {
                apiClient.validateToken(token);
                return chain.filter(exchange);
            } catch (Exception e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        };
    }

    public static class Config {
    }

    @Override
    public Class<Config> getConfigClass() {
        return Config.class;
    }

    @Override
    public Config newConfig() {
        return new Config();
    }
}
