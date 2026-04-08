package com.nutritrack.dto.food;

public class FoodResponse {
  private String id;
  private String name;
  private double calories;
  private double proteinG;
  private double ironMg;
  private double vitaminCMg;
  private double calciumMg;
  private double vitaminDIu;

  public FoodResponse() {}

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

