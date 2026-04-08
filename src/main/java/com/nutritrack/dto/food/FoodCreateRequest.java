package com.nutritrack.dto.food;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class FoodCreateRequest {
  @NotBlank
  private String id;

  @NotBlank
  private String name;

  private double calories;
  private double proteinG;
  private double ironMg;
  private double vitaminCMg;
  private double calciumMg;
  private double vitaminDIu;
}

