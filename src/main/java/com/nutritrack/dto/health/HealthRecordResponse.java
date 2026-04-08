package com.nutritrack.dto.health;

import java.util.Map;

public record HealthRecordResponse(
    String id,
    String userId,
    int age,
    Map<String, Object> totals,
    Map<String, Object> deficits,
    long timestamp
) {}

