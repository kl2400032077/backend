package com.nutritrack.service;

import com.nutritrack.dto.auth.AuthResponse;
import com.nutritrack.dto.auth.LoginRequest;
import com.nutritrack.dto.auth.SignupRequest;
import com.nutritrack.exception.ApiException;
import com.nutritrack.model.User;
import com.nutritrack.model.UserRole;
import com.nutritrack.repo.UserRepository;
import com.nutritrack.security.JwtService;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;

  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  public AuthResponse signup(SignupRequest req) {
    if (userRepository.existsByEmailIgnoreCase(req.getEmail())) {
      throw new ApiException(HttpStatus.CONFLICT, "Email already exists");
    }

    UserRole role = "admin".equalsIgnoreCase(req.getRole()) ? UserRole.ADMIN : UserRole.USER;
    User user = User.builder()
        .id("user_" + UUID.randomUUID())
        .email(req.getEmail().trim().toLowerCase())
        .passwordHash(passwordEncoder.encode(req.getPassword()))
        .role(role)
        .build();
    userRepository.save(user);

    String token = jwtService.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
    return AuthResponse.builder()
        .id(user.getId())
        .email(user.getEmail())
        .role(user.getRole().name().toLowerCase())
        .accessToken(token)
        .build();
  }

  public AuthResponse login(LoginRequest req) {
    User user = userRepository.findByEmailIgnoreCase(req.getEmail())
        .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

    if (!passwordEncoder.matches(req.getPassword(), user.getPasswordHash())) {
      throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }

    String token = jwtService.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
    return AuthResponse.builder()
        .id(user.getId())
        .email(user.getEmail())
        .role(user.getRole().name().toLowerCase())
        .accessToken(token)
        .build();
  }
}

