package com.SoloProject.solo.security;

import com.SoloProject.solo.models.User;
import com.SoloProject.solo.repos.UserRepo;
import com.SoloProject.solo.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtService jwtService;
    private final JwtAuthFilter jwtAuthFilter;
    private final UserRepo userRepo;

    public SecurityConfig(JwtService jwtService, JwtAuthFilter jwtAuthFilter, UserRepo userRepo) {
        this.jwtService = jwtService;
        this.jwtAuthFilter = jwtAuthFilter;
        this.userRepo = userRepo;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/error", "/oauth2/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .successHandler((request, response, authentication) -> {
                            OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

                            String email = oAuth2User.getAttribute("email");
                            String firstname = oAuth2User.getAttribute("given_name");
                            String lastname = oAuth2User.getAttribute("family_name");

                            //Create a new user
                            userRepo.findByEmail(email).orElseGet(() -> {
                                User newUser = new User();
                                newUser.setEmail(email);
                                newUser.setFirstName(firstname);
                                newUser.setLastName(lastname);
                                return userRepo.save(newUser);
                            });

                            String token = jwtService.generateToken(email);
                            response.sendRedirect("http://localhost:5173?token=" + token);
                        })
                )
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}