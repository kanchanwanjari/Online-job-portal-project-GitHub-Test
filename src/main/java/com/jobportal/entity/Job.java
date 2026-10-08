package com.jobportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

/**
 * Job vacancy entity posted by an Employer.
 */
@Entity
@Table(name = "jobs")
@Data
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "job_id")
    private Long jobId;

    @NotBlank(message = "Job title is required")
    @Column(nullable = false, length = 100)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(length = 100)
    private String company;

    @Column(length = 100)
    private String location;

    @Column(length = 100)
    private String salary;

    @Column(name = "posted_date")
    private LocalDate postedDate = LocalDate.now();

    // Foreign key -> Employer who posted this job
    // EAGER + JsonIgnoreProperties keeps plain-JSON serialization simple for this academic project
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "emp_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "password"})
    private Employer employer;
}
