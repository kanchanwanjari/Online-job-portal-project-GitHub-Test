package com.jobportal.service.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;    // for user/employer login
    private String username; // for admin login
    private String password;
}
