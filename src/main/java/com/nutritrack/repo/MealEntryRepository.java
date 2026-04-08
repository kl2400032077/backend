package com.nutritrack.repo;

import com.nutritrack.model.MealEntry;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MealEntryRepository extends JpaRepository<MealEntry, String> {
  List<MealEntry> findByUserIdOrderByAtAsc(String userId);
}

