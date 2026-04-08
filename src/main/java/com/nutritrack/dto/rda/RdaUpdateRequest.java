package com.nutritrack.dto.rda;

import lombok.Data;

@Data
public class RdaUpdateRequest {
  private Double calories;
  private Double proteinG;
  private Double ironMg;
  private Double vitaminCMg;
  private Double calciumMg;
  private Double vitaminDIu;
}

