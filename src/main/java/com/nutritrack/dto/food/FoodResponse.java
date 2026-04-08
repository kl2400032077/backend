package com.nutritrack.dto.food;

public record FoodResponse(
    String id,
    String name,
    double calories,
    double proteinG,
    double ironMg,
    double vitaminCMg,
    double calciumMg,
    double vitaminDIu
) {}

