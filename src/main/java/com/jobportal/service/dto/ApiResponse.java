package com.jobportal.service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Generic simple response wrapper used for success/error messages. */
@Data
@AllArgsConstructor
public class ApiResponse {
    private boolean success;
    private String message;
}
