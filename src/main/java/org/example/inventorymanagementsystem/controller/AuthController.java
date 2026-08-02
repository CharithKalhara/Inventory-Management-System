package org.example.inventorymanagementsystem.controller;

import jakarta.validation.Valid;
import org.example.inventorymanagementsystem.dto.LoginRequest;
import org.example.inventorymanagementsystem.dto.LoginResponse;
import org.example.inventorymanagementsystem.dto.RegisterRequest;
import org.example.inventorymanagementsystem.service.interfaces.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {

        authService.register(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @Valid @RequestBody LoginRequest request) {

        return ResponseEntity.ok(authService.login(request));
    }
}