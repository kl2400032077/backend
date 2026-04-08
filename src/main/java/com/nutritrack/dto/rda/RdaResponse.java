package com.nutritrack.dto.rda;

import lombok.Data;

@Data
public class RdaResponse {
  private String id;
  private String label;
  private double calories;
  private double proteinG;
  private double ironMg;
  private double vitaminCMg;
  private double calciumMg;
  private double vitaminDIu;
}

