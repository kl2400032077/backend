package com.nutritrack.controller;

import com.nutritrack.dto.auth.AuthResponse;
import com.nutritrack.dto.auth.LoginRequest;
import com.nutritrack.dto.auth.OtpRequest;
import com.nutritrack.dto.auth.SignupRequest;
import com.nutritrack.service.AuthService;
import jakarta.validation.Valid;
import java.util.Map;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
  private final AuthService authService;

  public AuthController(AuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/signup")
  public AuthResponse signup(@Valid @RequestBody SignupRequest req) {
    return authService.signup(req);
  }

  @PostMapping("/signup/request-otp")
  public Map<String, String> requestSignupOtp(@Valid @RequestBody OtpRequest req) {
    authService.requestSignupOtp(req.email());
    return Map.of("message", "OTP sent to email");
  }

  @PostMapping("/login")
  public AuthResponse login(@Valid @RequestBody LoginRequest req) {
    return authService.login(req);
  }
}

