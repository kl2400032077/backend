package com.nutritrack.dto.rda;

public record RdaUpdateRequest(
    Double calories,
    Double proteinG,
    Double ironMg,
    Double vitaminCMg,
    Double calciumMg,
    Double vitaminDIu
) {}

