package org.example.inventorymanagementsystem.service.interfaces;

import org.example.inventorymanagementsystem.dto.LoginRequest;
import org.example.inventorymanagementsystem.dto.LoginResponse;
import org.example.inventorymanagementsystem.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);

}