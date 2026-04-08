package com.nutritrack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "foods")
public class Food {
  @Id
  private String id;

  private String name;

  private double calories;
  private double proteinG;
  private double ironMg;
  private double vitaminCMg;
  private double calciumMg;
  private double vitaminDIu;

  public Food() {}

  public Food(String id, String name, double calories, double proteinG, double ironMg, double vitaminCMg, double calciumMg, double vitaminDIu) {
    this.id = id;
    this.name = name;
    this.calories = calories;
    this.proteinG = proteinG;
    this.ironMg = ironMg;
    this.vitaminCMg = vitaminCMg;
    this.calciumMg = calciumMg;
    this.vitaminDIu = vitaminDIu;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getCalories() {
    return calories;
  }

  public void setCalories(double calories) {
    this.calories = calories;
  }

  public double getProteinG() {
    return proteinG;
  }

  public void setProteinG(double proteinG) {
    this.proteinG = proteinG;
  }

  public double getIronMg() {
    return ironMg;
  }

  public void setIronMg(double ironMg) {
    this.ironMg = ironMg;
  }

  public double getVitaminCMg() {
    return vitaminCMg;
  }

  public void setVitaminCMg(double vitaminCMg) {
    this.vitaminCMg = vitaminCMg;
  }

  public double getCalciumMg() {
    return calciumMg;
  }

  public void setCalciumMg(double calciumMg) {
    this.calciumMg = calciumMg;
  }

  public double getVitaminDIu() {
    return vitaminDIu;
  }

  public void setVitaminDIu(double vitaminDIu) {
    this.vitaminDIu = vitaminDIu;
  }
}

