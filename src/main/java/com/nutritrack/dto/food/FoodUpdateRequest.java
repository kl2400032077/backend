package com.nutritrack.dto.food;

import lombok.Data;

@Data
public class FoodUpdateRequest {
  private String name;
  private Double calories;
  private Double proteinG;
  private Double ironMg;
  private Double vitaminCMg;
  private Double calciumMg;
  private Double vitaminDIu;
}

