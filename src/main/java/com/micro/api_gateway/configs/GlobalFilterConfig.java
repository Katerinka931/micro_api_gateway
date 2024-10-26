package com.micro.api_gateway.configs;

import com.micro.api_gateway.filters.TokenGlobalFilter;
import com.micro.api_gateway.services.ApiClient;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GlobalFilterConfig {
    private final ObjectProvider<ApiClient> apiClient;

    public GlobalFilterConfig(ObjectProvider<ApiClient> authClientProvider) {
        this.apiClient = authClientProvider;
    }

    @Bean
    public GlobalFilter customFilter() {
        return new TokenGlobalFilter(apiClient);
    }
}
