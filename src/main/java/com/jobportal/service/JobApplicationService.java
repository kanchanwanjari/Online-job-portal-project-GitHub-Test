package com.jobportal.service;

import com.jobportal.entity.Job;
import com.jobportal.entity.JobApplication;
import com.jobportal.entity.User;
import com.jobportal.repository.JobApplicationRepository;
import com.jobportal.repository.JobRepository;
import com.jobportal.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {

    private final JobApplicationRepository applicationRepository;
    private final UserRepository userRepository;
    private final JobRepository jobRepository;

    public JobApplication applyForJob(Long userId, Long jobId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));

        applicationRepository.findByUserAndJob(user, job).ifPresent(a -> {
            throw new IllegalStateException("You have already applied for this job");
        });

        JobApplication application = new JobApplication();
        application.setUser(user);
        application.setJob(job);
        application.setStatus("Applied");
        return applicationRepository.save(application);
    }

    public List<JobApplication> getApplicationsByUser(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return applicationRepository.findByUser(user);
    }

    public List<JobApplication> getApplicationsByJob(Long jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        return applicationRepository.findByJob(job);
    }

    public JobApplication updateStatus(Long appId, String status) {
        JobApplication application = applicationRepository.findById(appId)
                .orElseThrow(() -> new IllegalArgumentException("Application not found"));
        application.setStatus(status);
        return applicationRepository.save(application);
    }

    public List<JobApplication> getAllApplications() {
        return applicationRepository.findAll();
    }
}
