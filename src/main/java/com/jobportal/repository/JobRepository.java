package com.jobportal.repository;

import com.jobportal.entity.Employer;
import com.jobportal.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, Long> {
    List<Job> findByEmployer(Employer employer);

    List<Job> findByTitleContainingIgnoreCaseOrLocationContainingIgnoreCaseOrCompanyContainingIgnoreCase(
            String title, String location, String company);
}
