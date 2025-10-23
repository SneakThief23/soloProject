package com.SoloProject.solo.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Oauth2Controller {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @GetMapping("/test-jwt")
    public String testJwt() {
        return "JWT Secret: " + (jwtSecret != null ? "Yes" : "No");
    }
}