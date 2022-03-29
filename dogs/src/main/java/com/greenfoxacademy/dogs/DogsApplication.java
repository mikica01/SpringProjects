package com.greenfoxacademy.dogs;

import com.greenfoxacademy.dogs.models.entities.Dog;
import com.greenfoxacademy.dogs.repositories.DogRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DogsApplication implements CommandLineRunner {
  private final DogRepository repo;

  @Autowired
  public DogsApplication(DogRepository repo) {
    this.repo = repo;
  }

  public static void main(String[] args) {
    SpringApplication.run(DogsApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    repo.save(
        new Dog("Buksi", "male", "Commodore", 50.6, 35.6, "fülvakarás", LocalDate.of(1999, 3, 15)));
  }
}
