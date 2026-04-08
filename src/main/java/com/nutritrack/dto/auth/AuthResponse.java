package com.nutritrack.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {
  private String id;
  private String email;
  private String role;
  private String accessToken;
}

