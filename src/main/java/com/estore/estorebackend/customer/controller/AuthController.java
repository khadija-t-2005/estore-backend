package com.estore.estorebackend.customer.controller;

import com.estore.estorebackend.customer.dto.UserLoginDTO;
import com.estore.estorebackend.customer.dto.UserRegisterDTO;
import com.estore.estorebackend.customer.dto.UserResponseDTO;
import com.estore.estorebackend.customer.service.AuthService;
import com.estore.estorebackend.customer.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<UserResponseDTO> register(@RequestBody UserRegisterDTO dto) {
        return ResponseEntity.ok(authService.register(dto));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody UserLoginDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }
}
