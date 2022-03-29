package com.greenfoxacademy.dogs.repositories;

import com.greenfoxacademy.dogs.models.entities.Dog;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogRepository extends CrudRepository<Dog, Integer> {
  List<Dog> findAll();
}
