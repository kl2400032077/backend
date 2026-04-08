package com.nutritrack.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nutritrack.dto.meal.MealEntryRequest;
import com.nutritrack.dto.meal.MealEntryResponse;
import com.nutritrack.exception.ApiException;
import com.nutritrack.model.MealEntry;
import com.nutritrack.repo.MealEntryRepository;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class MealService {
  private final MealEntryRepository mealEntryRepository;
  private final ObjectMapper objectMapper;

  public MealService(MealEntryRepository mealEntryRepository, ObjectMapper objectMapper) {
    this.mealEntryRepository = mealEntryRepository;
    this.objectMapper = objectMapper;
  }

  public List<MealEntryResponse> list(String userId) {
    return mealEntryRepository.findByUserIdOrderByAtAsc(userId).stream().map(this::toResponse).toList();
  }

  public MealEntryResponse add(String userId, MealEntryRequest req) {
    if (req == null || req.foodIdOrName() == null || req.foodIdOrName().isBlank()) {
      throw new ApiException(HttpStatus.BAD_REQUEST, "foodIdOrName required");
    }
    double grams = req.grams() == null ? 100 : req.grams();
    boolean customFood = req.customFood() != null && req.customFood();
    String nutrientsJson = null;
    if (customFood && req.nutrients() != null) {
      try {
        nutrientsJson = objectMapper.writeValueAsString(req.nutrients());
      } catch (Exception e) {
        throw new ApiException(HttpStatus.BAD_REQUEST, "Invalid nutrients");
      }
    }
    MealEntry entry = new MealEntry(
        "meal_" + UUID.randomUUID(),
        userId,
        req.foodIdOrName(),
        grams,
        customFood,
        nutrientsJson,
        System.currentTimeMillis()
    );
    mealEntryRepository.save(entry);
    return toResponse(entry);
  }

  private MealEntryResponse toResponse(MealEntry e) {
    Map<String, Object> nutrients = null;
    if (e.getNutrientsJson() != null && !e.getNutrientsJson().isBlank()) {
      try {
        nutrients = objectMapper.readValue(e.getNutrientsJson(), new TypeReference<>() {});
      } catch (Exception ignored) {}
    }
    return new MealEntryResponse(e.getId(), e.getUserId(), e.getFoodIdOrName(), e.getGrams(), e.isCustomFood(), nutrients, e.getAt());
  }
}

