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
    if (foodRepository.existsById(req.getId())) {
      throw new ApiException(HttpStatus.CONFLICT, "id exists");
    }
    Food food = modelMapper.map(req, Food.class);
    foodRepository.save(food);
    return modelMapper.map(food, FoodResponse.class);
  }

  public FoodResponse update(String id, FoodUpdateRequest req) {
    Food food = foodRepository.findById(id).orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, "not found"));
    if (req.getName() != null) food.setName(req.getName());
    if (req.getCalories() != null) food.setCalories(req.getCalories());
    if (req.getProteinG() != null) food.setProteinG(req.getProteinG());
    if (req.getIronMg() != null) food.setIronMg(req.getIronMg());
    if (req.getVitaminCMg() != null) food.setVitaminCMg(req.getVitaminCMg());
    if (req.getCalciumMg() != null) food.setCalciumMg(req.getCalciumMg());
    if (req.getVitaminDIu() != null) food.setVitaminDIu(req.getVitaminDIu());
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

