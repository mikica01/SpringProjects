package com.greenfoxacademy.dogs.services;


import com.greenfoxacademy.dogs.models.entities.Dog;
import com.greenfoxacademy.dogs.models.entities.Toy;
import com.greenfoxacademy.dogs.models.exceptions.NoSuchDogException;
import java.util.List;

public interface DogService {
  List<Dog> getAll();
  void save(Dog dog);
  Dog addToy(Toy toy, String id);
  void deleteDog(String id);
  Dog findById(String id) throws NoSuchDogException;
  void updateDog(Integer id, Dog dog);
  boolean dogValidation(Dog dog) throws IllegalArgumentException;
}
