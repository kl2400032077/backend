package com.nutritrack.dto.meal;

import java.util.Map;

public record MealEntryRequest(
    String foodIdOrName,
    Double grams,
    Boolean customFood,
    Map<String, Object> nutrients
) {}

