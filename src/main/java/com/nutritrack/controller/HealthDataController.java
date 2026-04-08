package com.nutritrack.controller;

import com.nutritrack.dto.health.HealthDataRequest;
import com.nutritrack.dto.health.HealthRecordResponse;
import com.nutritrack.service.HealthService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HealthDataController {
  private final HealthService healthService;

  public HealthDataController(HealthService healthService) {
    this.healthService = healthService;
  }

  @PostMapping("/user/health-data")
  @ResponseStatus(HttpStatus.CREATED)
  public HealthRecordResponse save(@RequestBody HealthDataRequest req) {
    return healthService.save(req);
  }

  @GetMapping("/user/health-history/{userId}")
  public List<HealthRecordResponse> history(@PathVariable String userId) {
    return healthService.history(userId);
  }

  @GetMapping("/admin/health-data")
  public List<HealthRecordResponse> latestPerUser() {
    return healthService.latestPerUser();
  }
}

