package com.incubationHackathon.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class GatewaySecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
                .csrf().disable()
                .authorizeExchange()
                .pathMatchers("/users/login", "/users/status").permitAll() // Allow login and status without authentication
                .anyExchange().permitAll() // Ensure all other endpoints are also allowed
                .and()
                .httpBasic().disable()
                .formLogin().disable();
        return http.build();
    }
}

