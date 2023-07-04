package com.diepv.springsecuritydemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class HelloController {

    @GetMapping("/hello")
    public ResponseEntity<?> hello() {
        return ResponseEntity.ok().body("Hello World!");
    }
}
