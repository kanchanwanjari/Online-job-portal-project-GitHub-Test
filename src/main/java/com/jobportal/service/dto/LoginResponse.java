package com.jobportal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginResponse {
    private boolean success;
    private String message;
    private String role;   // USER, EMPLOYER, ADMIN
    private Long id;
    private String name;
}
