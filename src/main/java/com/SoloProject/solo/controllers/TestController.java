package com.SoloProject.solo.controllers;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class TestController {

    @GetMapping
    public String hello(Principal principal) {
        return "Hello, " + principal.getName() + ".";
    }
}
