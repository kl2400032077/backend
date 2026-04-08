package com.nutritrack.dto.food;

public record FoodUpdateRequest(
    String name,
    Double calories,
    Double proteinG,
    Double ironMg,
    Double vitaminCMg,
    Double calciumMg,
    Double vitaminDIu
) {}

