package com.jobportal.controller;

import com.jobportal.entity.Employer;
import com.jobportal.entity.User;
import com.jobportal.service.EmployerService;
import com.jobportal.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final EmployerService employerService;

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/employers")
    public List<Employer> getAllEmployers() {
        return employerService.getAllEmployers();
    }

    @DeleteMapping("/employers/{id}")
    public ResponseEntity<?> deleteEmployer(@PathVariable Long id) {
        employerService.deleteEmployer(id);
        return ResponseEntity.ok().build();
    }
}
