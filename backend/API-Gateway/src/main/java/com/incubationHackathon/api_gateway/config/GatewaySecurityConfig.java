package com.incubationHackathon.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class GatewaySecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable() // Disable CSRF as required
                .authorizeHttpRequests()
                .requestMatchers("/users/**", "/accounts/**", "/transactions/**").permitAll() // Public access to all endpoints
                .anyRequest().authenticated()
                .and()
                .formLogin().disable() // Disable form login if using JWT-based or other auth methods
                .httpBasic().disable(); // Optionally disable basic auth if not required

        return http.build();
    }
}

