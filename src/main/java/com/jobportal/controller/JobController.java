package com.jobportal.controller;

import com.jobportal.entity.Job;
import com.jobportal.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    /** Employer posts a new job. */
    @PostMapping("/post/{employerId}")
    public ResponseEntity<?> postJob(@PathVariable Long employerId, @Valid @RequestBody Job job) {
        try {
            return ResponseEntity.ok(jobService.postJob(employerId, job));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    /** All jobs - used on the job seeker search page. */
    @GetMapping
    public List<Job> getAllJobs() {
        return jobService.getAllJobs();
    }

    /** Search jobs by title / location / company keyword. */
    @GetMapping("/search")
    public List<Job> searchJobs(@RequestParam(required = false) String keyword) {
        return jobService.searchJobs(keyword);
    }

    /** Jobs posted by one specific employer. */
    @GetMapping("/employer/{employerId}")
    public List<Job> getJobsByEmployer(@PathVariable Long employerId) {
        return jobService.getJobsByEmployer(employerId);
    }

    @PutMapping("/{jobId}")
    public ResponseEntity<?> updateJob(@PathVariable Long jobId, @Valid @RequestBody Job job) {
        try {
            return ResponseEntity.ok(jobService.updateJob(jobId, job));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/{jobId}")
    public ResponseEntity<?> deleteJob(@PathVariable Long jobId) {
        jobService.deleteJob(jobId);
        return ResponseEntity.ok().build();
    }
}
