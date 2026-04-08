package com.nutritrack.service;

import com.nutritrack.dto.food.FoodCreateRequest;
import com.nutritrack.dto.food.FoodResponse;
import com.nutritrack.dto.food.FoodUpdateRequest;
import com.nutritrack.exception.ApiException;
import com.nutritrack.model.Food;
import com.nutritrack.repo.FoodRepository;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FoodService {
  private final FoodRepository foodRepository;
  private final ModelMapper modelMapper;

  public FoodService(FoodRepository foodRepository, ModelMapper modelMapper) {
    this.foodRepository = foodRepository;
    this.modelMapper = modelMapper;
  }

  public List<FoodResponse> list() {
    return foodRepository.findAll().stream().map(f -> modelMapper.map(f, FoodResponse.class)).toList();
  }

  public FoodResponse create(FoodCreateRequest req) {
    if (foodRepository.existsById(req.id())) {
      throw new ApiException(HttpStatus.CONFLICT, "id exists");
    }
    Food food = new Food(
        req.id(),
        req.name(),
        req.calories(),
        req.proteinG(),
        req.ironMg(),
        req.vitaminCMg(),
        req.calciumMg(),
        req.vitaminDIu()
    );
    foodRepository.save(food);
    return modelMapper.map(food, FoodResponse.class);
  }

  public FoodResponse update(String id, FoodUpdateRequest req) {
    Food food = foodRepository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "not found"));
    if (req.name() != null) food.setName(req.name());
    if (req.calories() != null) food.setCalories(req.calories());
    if (req.proteinG() != null) food.setProteinG(req.proteinG());
    if (req.ironMg() != null) food.setIronMg(req.ironMg());
    if (req.vitaminCMg() != null) food.setVitaminCMg(req.vitaminCMg());
    if (req.calciumMg() != null) food.setCalciumMg(req.calciumMg());
    if (req.vitaminDIu() != null) food.setVitaminDIu(req.vitaminDIu());
    foodRepository.save(food);
    return modelMapper.map(food, FoodResponse.class);
  }

  public void delete(String id) {
    if (!foodRepository.existsById(id)) {
      throw new ApiException(HttpStatus.NOT_FOUND, "not found");
    }
    foodRepository.deleteById(id);
  }
}

