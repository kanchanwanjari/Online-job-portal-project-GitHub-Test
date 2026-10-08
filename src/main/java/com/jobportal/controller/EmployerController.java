package com.jobportal.controller;

import com.jobportal.entity.Employer;
import com.jobportal.service.EmployerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employers")
@RequiredArgsConstructor
public class EmployerController {

    private final EmployerService employerService;

    @GetMapping("/{id}")
    public Employer getEmployer(@PathVariable Long id) {
        Employer employer = employerService.getEmployerById(id);
        employer.setPassword(null);
        return employer;
    }
}
