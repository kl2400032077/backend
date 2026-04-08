package com.nutritrack.dto.food;

import lombok.Data;

@Data
public class FoodResponse {
  private String id;
  private String name;
  private double calories;
  private double proteinG;
  private double ironMg;
  private double vitaminCMg;
  private double calciumMg;
  private double vitaminDIu;
}

