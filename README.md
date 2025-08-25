# Student Attendance + Library Management (Spring Boot 3)

## How to Run
1. Install JDK 17 and Maven.
2. Create a MySQL DB: `sms_db` and update `src/main/resources/application.yml` (username/password).
3. Build & run:
   ```bash
   mvn spring-boot:run
   ```
4. Open Swagger at: `http://localhost:8080/swagger-ui/index.html`

## Quick Start
- Signup an admin:
  ```http
  POST /auth/signup
  {
    "username": "admin",
    "password": "admin123",
    "role": "ADMIN",
    "fullName": "System Admin",
    "email": "admin@example.com"
  }
  ```
- Login:
  ```http
  POST /auth/login
  -> returns { "token": "..." }
  ```
- Use the token in `Authorization: Bearer <token>` for all protected endpoints.

## API Coverage
Implements:
- /auth/signup, /auth/login, /auth/profile
- /students CRUD
- /teachers CRUD
- /attendance/mark, /attendance/student/{id}, /attendance/class/{class}?date=YYYY-MM-DD, /attendance/summary/{id}
- /books CRUD
- /library/issue, /library/return, /library/student/{id}, /library/overdue

## Notes
- Role guard via `@PreAuthorize`.
- JWT via `Authorization: Bearer <token>` header.
- Simple fine calculation: 5 per overdue day.
