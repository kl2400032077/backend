package com.nutritrack.dto.rda;

public record RdaResponse(
    String id,
    String label,
    double calories,
    double proteinG,
    double ironMg,
    double vitaminCMg,
    double calciumMg,
    double vitaminDIu
) {}

