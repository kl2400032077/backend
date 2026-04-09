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
  private final OtpService otpService;

  public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, OtpService otpService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
    this.otpService = otpService;
  }

  public void requestSignupOtp(String email) {
    if (userRepository.existsByEmailIgnoreCase(email)) {
      throw new ApiException(HttpStatus.CONFLICT, "Email already exists");
    }
    otpService.issueSignupOtp(email);
  }

  public AuthResponse signup(SignupRequest req) {
    if (userRepository.existsByEmailIgnoreCase(req.email())) {
      throw new ApiException(HttpStatus.CONFLICT, "Email already exists");
    }
    otpService.verifySignupOtp(req.email(), req.otp().trim());

    UserRole role = "admin".equalsIgnoreCase(req.role()) ? UserRole.ADMIN : UserRole.USER;
    User user = new User(
        "user_" + UUID.randomUUID(),
        req.email().trim().toLowerCase(),
        passwordEncoder.encode(req.password()),
        role
    );
    userRepository.save(user);

    String token = jwtService.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
    return new AuthResponse(user.getId(), user.getEmail(), user.getRole().name().toLowerCase(), token);
  }

  public AuthResponse login(LoginRequest req) {
    User user = userRepository.findByEmailIgnoreCase(req.email())
        .orElseThrow(() -> new ApiException(HttpStatus.UNAUTHORIZED, "Invalid email or password"));

    if (!passwordEncoder.matches(req.password(), user.getPasswordHash())) {
      throw new ApiException(HttpStatus.UNAUTHORIZED, "Invalid email or password");
    }

    String token = jwtService.generateAccessToken(user.getId(), user.getEmail(), user.getRole().name());
    return new AuthResponse(user.getId(), user.getEmail(), user.getRole().name().toLowerCase(), token);
  }
}

