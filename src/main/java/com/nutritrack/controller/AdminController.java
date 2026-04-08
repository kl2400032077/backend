package com.nutritrack.controller;

import com.nutritrack.dto.auth.AuthResponse;
import com.nutritrack.model.User;
import com.nutritrack.repo.UserRepository;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AdminController {
  private final UserRepository userRepository;

  public AdminController(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @GetMapping("/users")
  public List<AuthResponse> users() {
    return userRepository.findAll().stream().map(this::toUserResponse).toList();
  }

  private AuthResponse toUserResponse(User u) {
    return AuthResponse.builder()
        .id(u.getId())
        .email(u.getEmail())
        .role(u.getRole().name().toLowerCase())
        .accessToken(null)
        .build();
  }
}

