package com.nutritrack.controller;

import com.nutritrack.dto.food.FoodCreateRequest;
import com.nutritrack.dto.food.FoodResponse;
import com.nutritrack.dto.food.FoodUpdateRequest;
import com.nutritrack.service.FoodService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/foods")
public class FoodController {
  private final FoodService foodService;

  public FoodController(FoodService foodService) {
    this.foodService = foodService;
  }

  @GetMapping
  public List<FoodResponse> list() {
    return foodService.list();
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public FoodResponse create(@Valid @RequestBody FoodCreateRequest req) {
    return foodService.create(req);
  }

  @PutMapping("/{id}")
  public FoodResponse update(@PathVariable String id, @RequestBody FoodUpdateRequest req) {
    return foodService.update(id, req);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable String id) {
    foodService.delete(id);
  }
}

