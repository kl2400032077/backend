package com.nutritrack.dto.auth;

public record AuthResponse(String id, String email, String role, String accessToken) {}

