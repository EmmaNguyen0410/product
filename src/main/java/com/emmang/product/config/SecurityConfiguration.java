package com.emmang.product.config;

import com.emmang.product.filter.AuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    private final AuthorizationFilter authorizationFilter;

    public SecurityConfiguration(AuthorizationFilter authorizationFilter) {
        this.authorizationFilter = authorizationFilter;
    }

    @Bean
    public SecurityFilterChain configure (HttpSecurity http) throws Exception {
        return http.csrf()
                .disable()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/product/guest-access-only").hasRole("GUEST")
                        .requestMatchers("/product/admin-access-only").hasRole("ADMIN")
                        .requestMatchers("/product/admin-access-call-admin-access").hasRole("ADMIN")
                        .requestMatchers("/product/admin-access-call-guest-access").hasRole("ADMIN")
                        .anyRequest().authenticated() // Authenticate all other requests
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless session
                .addFilterBefore(authorizationFilter, UsernamePasswordAuthenticationFilter.class) // Add JWT filter
                .build();
    }
}
