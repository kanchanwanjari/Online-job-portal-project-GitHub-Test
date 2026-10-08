package com.jobportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Online Job Portal System
 * MCA Project (MCSP-232) - IGNOU
 *
 * Entry point of the Spring Boot application.
 */
@SpringBootApplication
public class JobPortalApplication {
    public static void main(String[] args) {
        SpringApplication.run(JobPortalApplication.class, args);
        System.out.println("=======================================================");
        System.out.println(" Online Job Portal System started successfully!");
        System.out.println(" Open your browser at: http://localhost:8080/index.html");
        System.out.println("=======================================================");
    }
}
