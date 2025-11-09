package com.liftly.liftly.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // disable CSRF protection (useful for APIs)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // allow all endpoints
                )
                .httpBasic(Customizer.withDefaults()) // optional, keeps basic HTTP auth available
                .formLogin(form -> form.disable())   // disable form login
                .headers(headers -> headers.disable()); // disable all security headers (includes frameOptions)

        return http.build();
    }
}


