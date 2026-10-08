package com.jobportal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Employer entity - companies who post jobs.
 */
@Entity
@Table(name = "employers")
@Data
public class Employer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "emp_id")
    private Long empId;

    @NotBlank(message = "Company name is required")
    @Column(name = "company_name", nullable = false, length = 100)
    private String companyName;

    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @NotBlank(message = "Password is required")
    @Column(nullable = false, length = 255)
    private String password; // stored as BCrypt hash

    @Column(length = 100)
    private String location;
}
