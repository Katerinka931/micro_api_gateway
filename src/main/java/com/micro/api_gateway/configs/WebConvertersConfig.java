package com.micro.api_gateway.configs;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

@Configuration
public class WebConvertersConfig {
    @Bean
    public HttpMessageConverters customConverters() {
        List<HttpMessageConverter<?>> converters = List.of(
                new StringHttpMessageConverter(),
                new MappingJackson2HttpMessageConverter()
        );
        return new HttpMessageConverters(converters);
    }
}
