# NutriTrack Backend (Spring Boot)

## Requirements covered
- Swagger/OpenAPI
- DTOs + ModelMapper
- Global exception handling (`@ControllerAdvice`)
- JWT token generation + Spring Security

## Run

### Option A: Maven wrapper (recommended)
```bash
./mvnw spring-boot:run
```

### Option B: Windows
```bat
.\mvnw.cmd spring-boot:run
```

Backend runs on `http://localhost:8080`.

## Swagger
- Swagger UI: `http://localhost:8080/swagger-ui/index.html`
- OpenAPI JSON: `http://localhost:8080/v3/api-docs`

## Demo accounts
- User: `user@example.com` / `user123`
- Admin: `admin@example.com` / `admin123`

## Auth flow (JWT)
1. `POST /api/auth/login` or `POST /api/auth/signup`
2. Copy `accessToken` from response
3. Use `Authorization: Bearer <token>` for protected endpoints

