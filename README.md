# Online Job Portal System

MCA Project (MCSP-232, IGNOU) — a web-based application connecting **Job Seekers** and
**Employers**, built with **Spring Boot**, **MySQL**, and **HTML/CSS/JavaScript**, matching
your submitted project proposal.

## Tech Stack
- **Backend:** Java 17, Spring Boot 3.2.5 (Spring Web, Spring Data JPA)
- **Database:** MySQL
- **Frontend:** HTML, CSS, vanilla JavaScript (served as static files by Spring Boot)
- **Security:** Passwords hashed with BCrypt

## Project Modules
- **Admin Module** — view/manage job seekers and employers
- **Employer Module** — register/login, post jobs, view & update applicant status
- **Job Seeker Module** — register/login, search jobs, apply, track application status
- **Job Management Module** — CRUD for job postings
- **Application Module** — links job seekers to jobs, tracks status (Applied / Shortlisted / Selected / Rejected)

## Folder Structure
```
job-portal-project/
├── pom.xml
├── database/schema.sql          (reference only — auto-created by Hibernate)
└── src/main/
    ├── java/com/jobportal/
    │   ├── JobPortalApplication.java
    │   ├── entity/        (User, Employer, Job, JobApplication, Admin)
    │   ├── repository/    (Spring Data JPA repositories)
    │   ├── service/       (business logic + DTOs)
    │   ├── controller/    (REST APIs)
    │   └── config/        (CORS, password encoder, error handling)
    └── resources/
        ├── application.properties
        └── static/        (frontend: index.html, css/, js/, pages/)
```

## Prerequisites
1. **Java 17+** installed (`java -version`)
2. **Maven** installed (`mvn -version`) — or use an IDE (Eclipse / IntelliJ) that manages it
3. **MySQL Server** running locally (default port 3306)
4. Internet connection the **first time you build** (Maven needs to download dependencies
   from Maven Central — this could not be pre-downloaded in this sandbox, so you must build
   it on your own machine)

## Setup Steps

### 1. Configure the database connection
Open `src/main/resources/application.properties` and update the username/password to match
your local MySQL installation:
```properties
spring.datasource.username=root
spring.datasource.password=root
```
You do **not** need to manually create tables — `spring.jpa.hibernate.ddl-auto=update`
creates the database (`job_portal_db`) and all tables automatically on first run.
(`database/schema.sql` is provided purely for reference/documentation.)

### 2. Build and run

**Option A — Command line (Maven):**
```bash
cd job-portal-project
mvn spring-boot:run
```

**Option B — IDE (Eclipse / IntelliJ):**
1. Import the folder as an existing Maven project.
2. Let it download dependencies.
3. Run `JobPortalApplication.java` as a Java Application.

### 3. Open the application
Once you see `Online Job Portal System started successfully!` in the console, open:
```
http://localhost:8080/index.html
```

### 4. Default Admin Login
A default admin account is auto-created on first startup:
- **Username:** `admin`
- **Password:** `admin123`

## How to Use
1. From the home page, choose **Job Seeker**, **Employer**, or **Admin**.
2. **Employer** registers → logs in → posts jobs → views applicants → updates their status.
3. **Job Seeker** registers → logs in → searches jobs → applies → tracks application status.
4. **Admin** logs in → views/manages all registered users and employers, and sees all
   job postings & applications across the system.

## REST API Overview (for reference / demo in your viva)

| Method | Endpoint | Purpose |
|---|---|---|
| POST | `/api/auth/register/user` | Register job seeker |
| POST | `/api/auth/register/employer` | Register employer |
| POST | `/api/auth/login/user` | Job seeker login |
| POST | `/api/auth/login/employer` | Employer login |
| POST | `/api/auth/login/admin` | Admin login |
| GET | `/api/jobs` | List all jobs |
| GET | `/api/jobs/search?keyword=` | Search jobs |
| POST | `/api/jobs/post/{employerId}` | Post a job |
| PUT | `/api/jobs/{jobId}` | Update a job |
| DELETE | `/api/jobs/{jobId}` | Delete a job |
| POST | `/api/applications/apply?userId=&jobId=` | Apply for a job |
| GET | `/api/applications/user/{userId}` | My applications (job seeker) |
| GET | `/api/applications/job/{jobId}` | Applicants for a job (employer) |
| PUT | `/api/applications/{appId}/status` | Update application status |
| GET | `/api/admin/users` / `/api/admin/employers` | Admin: list users/employers |

## Notes
- This is an academic/demo project — for production use you would add JWT-based
  authentication, file upload handling for resumes, and stronger role-based security
  (e.g. Spring Security filters) instead of the simplified session approach used here
  (`sessionStorage` on the frontend).
- Passwords are hashed with BCrypt before being stored — they are never stored or
  returned in plain text.
