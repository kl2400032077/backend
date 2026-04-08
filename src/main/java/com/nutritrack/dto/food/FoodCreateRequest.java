package com.nutritrack.dto.food;

import jakarta.validation.constraints.NotBlank;

public record FoodCreateRequest(
    @NotBlank String id,
    @NotBlank String name,
    double calories,
    double proteinG,
    double ironMg,
    double vitaminCMg,
    double calciumMg,
    double vitaminDIu
) {}

