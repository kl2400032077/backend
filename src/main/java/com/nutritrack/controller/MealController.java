package com.nutritrack.controller;

import com.nutritrack.dto.meal.MealEntryRequest;
import com.nutritrack.dto.meal.MealEntryResponse;
import com.nutritrack.service.MealService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/meals")
public class MealController {
  private final MealService mealService;

  public MealController(MealService mealService) {
    this.mealService = mealService;
  }

  @GetMapping("/{userId}")
  public List<MealEntryResponse> list(@PathVariable String userId) {
    return mealService.list(userId);
  }

  @PostMapping("/{userId}")
  @ResponseStatus(HttpStatus.CREATED)
  public MealEntryResponse add(@PathVariable String userId, @RequestBody MealEntryRequest req) {
    return mealService.add(userId, req);
  }
}

