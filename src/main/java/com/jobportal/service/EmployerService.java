package com.jobportal.service;

import com.jobportal.entity.Employer;
import com.jobportal.repository.EmployerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployerService {

    private final EmployerRepository employerRepository;

    public List<Employer> getAllEmployers() {
        return employerRepository.findAll();
    }

    public Employer getEmployerById(Long id) {
        return employerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employer not found"));
    }

    public void deleteEmployer(Long id) {
        employerRepository.deleteById(id);
    }
}
