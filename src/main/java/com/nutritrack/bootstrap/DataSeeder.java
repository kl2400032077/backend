package com.nutritrack.bootstrap;

import com.nutritrack.model.Food;
import com.nutritrack.model.Rda;
import com.nutritrack.model.User;
import com.nutritrack.model.UserRole;
import com.nutritrack.repo.FoodRepository;
import com.nutritrack.repo.RdaRepository;
import com.nutritrack.repo.UserRepository;
import java.util.List;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataSeeder implements CommandLineRunner {
  private final UserRepository userRepository;
  private final FoodRepository foodRepository;
  private final RdaRepository rdaRepository;
  private final PasswordEncoder passwordEncoder;

  public DataSeeder(UserRepository userRepository, FoodRepository foodRepository, RdaRepository rdaRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.foodRepository = foodRepository;
    this.rdaRepository = rdaRepository;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public void run(String... args) {
    seedUsers();
    seedFoods();
    seedRdas();
  }

  private void seedUsers() {
    if (userRepository.count() > 0) return;
    userRepository.saveAll(List.of(
        new User("u1", "user@example.com", passwordEncoder.encode("user123"), UserRole.USER),
        new User("a1", "admin@example.com", passwordEncoder.encode("admin123"), UserRole.ADMIN)
    ));
  }

  private void seedFoods() {
    if (foodRepository.count() > 0) return;
    foodRepository.saveAll(List.of(
        new Food("apple", "Apple", 52, 0.3, 0.1, 4.6, 6, 0),
        new Food("banana", "Banana", 96, 1.3, 0.3, 8.7, 5, 0),
        new Food("milk", "Milk (cow)", 61, 3.2, 0, 0, 113, 52),
        new Food("egg", "Egg", 155, 13, 1.2, 0, 50, 82),
        new Food("spinach", "Spinach", 23, 2.9, 2.7, 28.1, 99, 0)
    ));
  }

  private void seedRdas() {
    if (rdaRepository.count() > 0) return;
    rdaRepository.saveAll(List.of(
        new Rda("4-8", "Age 4-8", 1400, 19, 10, 25, 1000, 600),
        new Rda("9-13", "Age 9-13", 1800, 34, 8, 45, 1300, 600),
        new Rda("14-18", "Age 14-18", 2200, 46, 11, 65, 1300, 600)
    ));
  }
}

