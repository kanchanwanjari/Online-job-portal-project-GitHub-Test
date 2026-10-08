-- ================================================================
-- Online Job Portal System - Database Schema (reference)
-- NOTE: You do NOT need to run this manually. Spring Boot + Hibernate
-- will auto-create/update these tables on startup because
-- spring.jpa.hibernate.ddl-auto=update in application.properties.
-- This file is kept for documentation and matches the ER Diagram /
-- table structure described in the project proposal.
-- ================================================================

CREATE DATABASE IF NOT EXISTS job_portal_db;
USE job_portal_db;

CREATE TABLE IF NOT EXISTS users (
    user_id   INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    email     VARCHAR(100) NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    phone     VARCHAR(15),
    resume    VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS employers (
    emp_id       INT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(100) NOT NULL,
    email        VARCHAR(100) NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    location     VARCHAR(100)
);

CREATE TABLE IF NOT EXISTS jobs (
    job_id      INT AUTO_INCREMENT PRIMARY KEY,
    title       VARCHAR(100) NOT NULL,
    description TEXT,
    company     VARCHAR(100),
    location    VARCHAR(100),
    salary      VARCHAR(100),
    posted_date DATE,
    emp_id      INT NOT NULL,
    FOREIGN KEY (emp_id) REFERENCES employers(emp_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS applications (
    app_id       INT AUTO_INCREMENT PRIMARY KEY,
    user_id      INT NOT NULL,
    job_id       INT NOT NULL,
    status       VARCHAR(50) DEFAULT 'Applied',
    applied_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    FOREIGN KEY (job_id) REFERENCES jobs(job_id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS admins (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);
