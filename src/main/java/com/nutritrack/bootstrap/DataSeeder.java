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
        User.builder().id("u1").email("user@example.com").passwordHash(passwordEncoder.encode("user123")).role(UserRole.USER).build(),
        User.builder().id("a1").email("admin@example.com").passwordHash(passwordEncoder.encode("admin123")).role(UserRole.ADMIN).build()
    ));
  }

  private void seedFoods() {
    if (foodRepository.count() > 0) return;
    foodRepository.saveAll(List.of(
        Food.builder().id("apple").name("Apple").calories(52).proteinG(0.3).ironMg(0.1).vitaminCMg(4.6).calciumMg(6).vitaminDIu(0).build(),
        Food.builder().id("banana").name("Banana").calories(96).proteinG(1.3).ironMg(0.3).vitaminCMg(8.7).calciumMg(5).vitaminDIu(0).build(),
        Food.builder().id("milk").name("Milk (cow)").calories(61).proteinG(3.2).ironMg(0).vitaminCMg(0).calciumMg(113).vitaminDIu(52).build(),
        Food.builder().id("egg").name("Egg").calories(155).proteinG(13).ironMg(1.2).vitaminCMg(0).calciumMg(50).vitaminDIu(82).build(),
        Food.builder().id("spinach").name("Spinach").calories(23).proteinG(2.9).ironMg(2.7).vitaminCMg(28.1).calciumMg(99).vitaminDIu(0).build()
    ));
  }

  private void seedRdas() {
    if (rdaRepository.count() > 0) return;
    rdaRepository.saveAll(List.of(
        Rda.builder().id("4-8").label("Age 4-8").calories(1400).proteinG(19).ironMg(10).vitaminCMg(25).calciumMg(1000).vitaminDIu(600).build(),
        Rda.builder().id("9-13").label("Age 9-13").calories(1800).proteinG(34).ironMg(8).vitaminCMg(45).calciumMg(1300).vitaminDIu(600).build(),
        Rda.builder().id("14-18").label("Age 14-18").calories(2200).proteinG(46).ironMg(11).vitaminCMg(65).calciumMg(1300).vitaminDIu(600).build()
    ));
  }
}

