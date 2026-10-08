package com.jobportal.controller;

import com.jobportal.entity.JobApplication;
import com.jobportal.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/applications")
@RequiredArgsConstructor
public class JobApplicationController {

    private final JobApplicationService applicationService;

    /** Job seeker applies for a job. */
    @PostMapping("/apply")
    public ResponseEntity<?> apply(@RequestParam Long userId, @RequestParam Long jobId) {
        try {
            return ResponseEntity.ok(applicationService.applyForJob(userId, jobId));
        } catch (IllegalArgumentException | IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /** Job seeker views their own applications and statuses. */
    @GetMapping("/user/{userId}")
    public List<JobApplication> getByUser(@PathVariable Long userId) {
        return applicationService.getApplicationsByUser(userId);
    }

    /** Employer views applicants for one of their jobs. */
    @GetMapping("/job/{jobId}")
    public List<JobApplication> getByJob(@PathVariable Long jobId) {
        return applicationService.getApplicationsByJob(jobId);
    }

    /** Employer/Admin updates an application's status. */
    @PutMapping("/{appId}/status")
    public ResponseEntity<?> updateStatus(@PathVariable Long appId, @RequestBody Map<String, String> body) {
        try {
            return ResponseEntity.ok(applicationService.updateStatus(appId, body.get("status")));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /** Admin - view all applications in the system. */
    @GetMapping
    public List<JobApplication> getAll() {
        return applicationService.getAllApplications();
    }
}
