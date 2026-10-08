package com.jobportal.controller;

import com.jobportal.entity.Employer;
import com.jobportal.entity.User;
import com.jobportal.service.AuthService;
import com.jobportal.service.dto.ApiResponse;
import com.jobportal.service.dto.LoginRequest;
import com.jobportal.service.dto.LoginResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register/user")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            User saved = authService.registerUser(user);
            saved.setPassword(null); // never send the hash back
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
        }
    }

    @PostMapping("/register/employer")
    public ResponseEntity<?> registerEmployer(@Valid @RequestBody Employer employer) {
        try {
            Employer saved = authService.registerEmployer(employer);
            saved.setPassword(null);
            return ResponseEntity.ok(saved);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, ex.getMessage()));
        }
    }

    @PostMapping("/login/user")
    public ResponseEntity<LoginResponse> loginUser(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginUser(request));
    }

    @PostMapping("/login/employer")
    public ResponseEntity<LoginResponse> loginEmployer(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginEmployer(request));
    }

    @PostMapping("/login/admin")
    public ResponseEntity<LoginResponse> loginAdmin(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.loginAdmin(request));
    }
}
