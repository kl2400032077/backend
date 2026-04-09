# NutriTrack Backend (Spring Boot)

This folder is meant to be the **root of the backend Git repository** (submit this project separately from the frontend).

## Requirements covered
- **Swagger / OpenAPI** — `springdoc-openapi`, see URLs below
- **DTOs** — `com.nutritrack.dto.*` (e.g. `FoodCreateRequest`, `LoginRequest`)
- **ModelMapper** — entity ↔ DTO mapping in services
- **Global exception handling** (`@ControllerAdvice`) — `GlobalExceptionHandler`
- **JWT** — `JwtService`, `JwtAuthFilter`, Spring Security rules
- **OAuth** — optional; not included

## Run

### Option A: Maven wrapper (recommended)
```bash
./mvnw spring-boot:run
```

### Option B: Windows
```bat
.\mvnw.cmd spring-boot:run
```

### Eclipse / STS
File → Import → **Maven** → **Existing Maven Projects** → select this `backend` folder → Finish.  
Run `com.nutritrack.NutriTrackApplication` as **Java Application** or **Spring Boot App**.

Backend listens on `http://localhost:8080`.

## Database (MySQL Workbench)
- This backend now uses **MySQL** (not H2).
- Default connection in `application.yml`:
  - URL: `jdbc:mysql://localhost:3306/nutritrack?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC`
  - Username: `root`
  - Password: `root`

### Setup in MySQL Workbench
1. Open MySQL Workbench and ensure your MySQL server is running.
2. If your root password is not `root`, update `spring.datasource.password` in `application.yml`.
3. You can create DB manually with:
   ```sql
   CREATE DATABASE IF NOT EXISTS nutritrack;
   ```
   (The app URL already includes `createDatabaseIfNotExist=true`, so this is optional.)
4. Start backend: `.\mvnw.cmd spring-boot:run`
5. Refresh schemas in Workbench and open `nutritrack` to view tables/data.

## Swagger
- **Swagger UI:** `http://localhost:8080/swagger-ui/index.html`
- **OpenAPI JSON:** `http://localhost:8080/v3/api-docs`

## Demo accounts
- User: `user@example.com` / `user123`
- Admin: `admin@example.com` / `admin123`

## Auth flow (JWT)
1. `POST /api/auth/login` or `POST /api/auth/signup`
2. Response includes `accessToken` and `role` (lowercase: `admin` / `user`)
3. For protected endpoints use header: `Authorization: Bearer <accessToken>`
4. In Swagger: **Authorize** → `bearerAuth` → paste token only

## Pairing with the frontend repo
The **NutriTrack frontend** is a separate repo. Start this backend first; the SPA (Vite) expects the API at `http://localhost:8080` by default.

### Publish this folder as its own Git repo
Create an **empty** `nutritrack-backend` repository on GitHub/GitLab, then from **this directory**:

```bash
git remote add origin https://github.com/YOUR_USER/nutritrack-backend.git
git branch -M main
git push -u origin main
```

(If you still use the combined workspace, see `REPOSITORY_SETUP.md` there for the full two-repo picture.)
