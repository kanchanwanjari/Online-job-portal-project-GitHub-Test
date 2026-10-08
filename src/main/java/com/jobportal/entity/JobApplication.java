package com.jobportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

/**
 * Job Application entity - links a User (job seeker) to a Job they applied for.
 * Named "JobApplication" in Java (java.lang.reflect already has "Application"-like
 * concerns) but maps to the "applications" table described in the proposal.
 */
@Entity
@Table(name = "applications")
@Data
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "app_id")
    private Long appId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "password"})
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "job_id", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "password"})
    private Job job;

    @Column(length = 50)
    private String status = "Applied"; // Applied, Shortlisted, Rejected, Selected

    @Column(name = "applied_date")
    private LocalDate appliedDate = LocalDate.now();
}
