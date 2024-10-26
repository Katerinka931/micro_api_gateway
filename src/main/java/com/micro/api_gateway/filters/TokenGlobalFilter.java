package com.micro.api_gateway.filters;

import com.micro.api_gateway.models.UserDetails;
import com.micro.api_gateway.services.ApiClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class TokenGlobalFilter implements GlobalFilter, Ordered {
    private final ObjectProvider<ApiClient> apiClientObjectProvider;

    public TokenGlobalFilter(ObjectProvider<ApiClient> apiClientProvider) {
        this.apiClientObjectProvider = apiClientProvider;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        String token = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (token == null || !token.startsWith("Bearer "))
            request.mutate().header("token-info", ";none");
        else {
            ApiClient apiClient = apiClientObjectProvider.getObject();
            CompletableFuture<UserDetails> future = CompletableFuture.supplyAsync(() ->
                    apiClient.validateToken(token));

            UserDetails tokenInfo;
            try {
                tokenInfo = future.get();
            } catch (Exception ex) {
                ex.printStackTrace();
                tokenInfo = null;
            }

            if (tokenInfo == null)
                request.mutate().header("Token-Info", ";broken");
            else
                request.mutate().header("Token-Info", tokenInfo.getUsername() + ";" + tokenInfo.getRole());
        }

        return chain.filter(exchange).then(Mono.fromRunnable(() -> response.beforeCommit(() -> {
            response.getHeaders().remove("Token-Info");
            return Mono.empty();
        })));
    }

    @Override
    public int getOrder() {
        return -1;
    }
}
