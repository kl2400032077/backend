package com.nutritrack.dto.meal;

import java.util.Map;

public record MealEntryResponse(
    String id,
    String userId,
    String foodIdOrName,
    double grams,
    boolean customFood,
    Map<String, Object> nutrients,
    long at
) {}

