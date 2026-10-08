package com.jobportal.service;

import com.jobportal.entity.Employer;
import com.jobportal.entity.Job;
import com.jobportal.repository.EmployerRepository;
import com.jobportal.repository.JobRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;
    private final EmployerRepository employerRepository;

    public Job postJob(Long employerId, Job job) {
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new IllegalArgumentException("Employer not found"));
        job.setEmployer(employer);
        if (job.getCompany() == null || job.getCompany().isBlank()) {
            job.setCompany(employer.getCompanyName());
        }
        return jobRepository.save(job);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> getJobsByEmployer(Long employerId) {
        Employer employer = employerRepository.findById(employerId)
                .orElseThrow(() -> new IllegalArgumentException("Employer not found"));
        return jobRepository.findByEmployer(employer);
    }

    public List<Job> searchJobs(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            return jobRepository.findAll();
        }
        return jobRepository
                .findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCaseOrCompanyContainingIgnoreCase(
                        keyword, keyword, keyword);
    }

    public Job updateJob(Long jobId, Job updated) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job not found"));
        job.setTitle(updated.getTitle());
        job.setDescription(updated.getDescription());
        job.setLocation(updated.getLocation());
        job.setSalary(updated.getSalary());
        return jobRepository.save(job);
    }

    public void deleteJob(Long jobId) {
        jobRepository.deleteById(jobId);
    }
}
