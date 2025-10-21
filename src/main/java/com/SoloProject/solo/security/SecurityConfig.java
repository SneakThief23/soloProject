package com.SoloProject.solo.security;

import com.SoloProject.solo.service.JwtService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // allow public access to OAuth endpoints and frontend index
                        .requestMatchers("/", "/error", "/oauth2/**").permitAll()
                        // everything else requires authentication
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .successHandler((request, response, authentication) -> {
                            // Generate JWT after Google login
                            String email = authentication.getName();
                            String token = jwtService.generateToken(email);

                            // Redirect to React frontend with token
                            response.sendRedirect("http://localhost:5173?token=" + token);
                        })
                )
                .exceptionHandling(e -> e
                        .authenticationEntryPoint((req, res, ex) ->
                                res.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                );

        return http.build();
    }
}