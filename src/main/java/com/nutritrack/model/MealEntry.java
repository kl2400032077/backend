package com.nutritrack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meal_entries")
public class MealEntry {
  @Id
  private String id;

  @Column(nullable = false)
  private String userId;

  @Column(nullable = false)
  private String foodIdOrName;

  private double grams;

  private boolean customFood;

  @Column(columnDefinition = "LONGTEXT")
  private String nutrientsJson;

  private long at;

  public MealEntry() {}

  public MealEntry(String id, String userId, String foodIdOrName, double grams, boolean customFood, String nutrientsJson, long at) {
    this.id = id;
    this.userId = userId;
    this.foodIdOrName = foodIdOrName;
    this.grams = grams;
    this.customFood = customFood;
    this.nutrientsJson = nutrientsJson;
    this.at = at;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getUserId() {
    return userId;
  }

  public void setUserId(String userId) {
    this.userId = userId;
  }

  public String getFoodIdOrName() {
    return foodIdOrName;
  }

  public void setFoodIdOrName(String foodIdOrName) {
    this.foodIdOrName = foodIdOrName;
  }

  public double getGrams() {
    return grams;
  }

  public void setGrams(double grams) {
    this.grams = grams;
  }

  public boolean isCustomFood() {
    return customFood;
  }

  public void setCustomFood(boolean customFood) {
    this.customFood = customFood;
  }

  public String getNutrientsJson() {
    return nutrientsJson;
  }

  public void setNutrientsJson(String nutrientsJson) {
    this.nutrientsJson = nutrientsJson;
  }

  public long getAt() {
    return at;
  }

  public void setAt(long at) {
    this.at = at;
  }
}

