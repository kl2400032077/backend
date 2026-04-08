package com.nutritrack.repo;

import com.nutritrack.model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, String> {}

