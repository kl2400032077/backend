package com.nutritrack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "rdas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Rda {
  @Id
  private String id;

  private String label;

  private double calories;
  private double proteinG;
  private double ironMg;
  private double vitaminCMg;
  private double calciumMg;
  private double vitaminDIu;
}

