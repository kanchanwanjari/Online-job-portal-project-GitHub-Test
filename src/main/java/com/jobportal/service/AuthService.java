package com.jobportal.service;

import com.jobportal.entity.Admin;
import com.jobportal.entity.Employer;
import com.jobportal.entity.User;
import com.jobportal.repository.AdminRepository;
import com.jobportal.repository.EmployerRepository;
import com.jobportal.repository.UserRepository;
import com.jobportal.service.dto.LoginRequest;
import com.jobportal.service.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final EmployerRepository employerRepository;
    private final AdminRepository adminRepository;
    private final PasswordEncoder passwordEncoder;

    /** Registers a new Job Seeker. Password is hashed before saving. */
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new IllegalArgumentException("A user with this email already exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    /** Registers a new Employer. Password is hashed before saving. */
    public Employer registerEmployer(Employer employer) {
        if (employerRepository.existsByEmail(employer.getEmail())) {
            throw new IllegalArgumentException("An employer with this email already exists");
        }
        employer.setPassword(passwordEncoder.encode(employer.getPassword()));
        return employerRepository.save(employer);
    }

    /** Job Seeker login. */
    public LoginResponse loginUser(LoginRequest request) {
        return userRepository.findByEmail(request.getEmail())
                .filter(u -> passwordEncoder.matches(request.getPassword(), u.getPassword()))
                .map(u -> new LoginResponse(true, "Login successful", "USER", u.getUserId(), u.getName()))
                .orElse(new LoginResponse(false, "Invalid email or password", null, null, null));
    }

    /** Employer login. */
    public LoginResponse loginEmployer(LoginRequest request) {
        return employerRepository.findByEmail(request.getEmail())
                .filter(e -> passwordEncoder.matches(request.getPassword(), e.getPassword()))
                .map(e -> new LoginResponse(true, "Login successful", "EMPLOYER", e.getEmpId(), e.getCompanyName()))
                .orElse(new LoginResponse(false, "Invalid email or password", null, null, null));
    }

    /** Admin login. */
    public LoginResponse loginAdmin(LoginRequest request) {
        return adminRepository.findByUsername(request.getUsername())
                .filter(a -> passwordEncoder.matches(request.getPassword(), a.getPassword()))
                .map(a -> new LoginResponse(true, "Login successful", "ADMIN", a.getAdminId(), a.getUsername()))
                .orElse(new LoginResponse(false, "Invalid username or password", null, null, null));
    }
}
