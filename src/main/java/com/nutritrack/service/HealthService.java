package com.nutritrack.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutritrack.dto.health.HealthDataRequest;
import com.nutritrack.dto.health.HealthRecordResponse;
import com.nutritrack.exception.ApiException;
import com.nutritrack.model.HealthRecord;
import com.nutritrack.repo.HealthRecordRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class HealthService {
  private final HealthRecordRepository healthRecordRepository;
  private final ObjectMapper objectMapper;

  public HealthService(HealthRecordRepository healthRecordRepository, ObjectMapper objectMapper) {
    this.healthRecordRepository = healthRecordRepository;
    this.objectMapper = objectMapper;
  }

  public HealthRecordResponse save(HealthDataRequest req) {
    if (req == null || req.userId() == null || req.userId().isBlank()) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "userId required");
    }
    long ts = req.timestamp() != null ? req.timestamp() : System.currentTimeMillis();
    String totalsJson = toJson(req.totals());
    String deficitsJson = toJson(req.deficits());
    int age = req.age() != null ? req.age() : 0;
    HealthRecord rec = new HealthRecord("health_" + UUID.randomUUID(), req.userId(), age, totalsJson, deficitsJson, ts);
    healthRecordRepository.save(rec);
    return toResponse(rec);
  }

  public List<HealthRecordResponse> history(String userId) {
    return healthRecordRepository.findByUserIdOrderByTimestampAsc(userId).stream().map(this::toResponse).toList();
  }

  public List<HealthRecordResponse> latestPerUser() {
    Map<String, HealthRecord> latest = new HashMap<>();
    for (HealthRecord r : healthRecordRepository.findAll()) {
      HealthRecord existing = latest.get(r.getUserId());
      if (existing == null || r.getTimestamp() > existing.getTimestamp()) {
        latest.put(r.getUserId(), r);
      }
    }
    return latest.values().stream().map(this::toResponse).toList();
  }

  private String toJson(Map<String, Object> obj) {
    if (obj == null) return null;
    try {
      return objectMapper.writeValueAsString(obj);
    } catch (Exception e) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid payload");
    }
  }

  private HealthRecordResponse toResponse(HealthRecord r) {
    Map<String, Object> totals = fromJson(r.getTotalsJson());
    Map<String, Object> deficits = fromJson(r.getDeficitsJson());
    return new HealthRecordResponse(r.getId(), r.getUserId(), r.getAge(), totals, deficits, r.getTimestamp());
  }

  private Map<String, Object> fromJson(String json) {
    if (json == null || json.isBlank()) return null;
    try {
      return objectMapper.readValue(json, new TypeReference<>() {});
    } catch (Exception ignored) {
      return null;
    }
  }
}

