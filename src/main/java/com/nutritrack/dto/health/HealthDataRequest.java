package com.nutritrack.dto.health;

import java.util.Map;

public record HealthDataRequest(
    String userId,
    Integer age,
    Map<String, Object> totals,
    Map<String, Object> deficits,
    Long timestamp
) {}

