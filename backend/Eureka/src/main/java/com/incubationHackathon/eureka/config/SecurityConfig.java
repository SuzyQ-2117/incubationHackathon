package com.incubationHackathon.eureka.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF for Eureka endpoints
                .csrf(csrf -> csrf
                        .ignoringRequestMatchers("/eureka/**")
                )
                .authorizeHttpRequests(auth -> auth
                        // Allow all access to Eureka endpoints
                        .requestMatchers("/eureka/**").permitAll()
                        // Require authentication for all other requests
                        .anyRequest().authenticated()
                )
                // Enable HTTP Basic Authentication
                .httpBasic();
        return http.build();
    }
}
