package com.greenfoxacademy.dogs.services;

import com.greenfoxacademy.dogs.models.entities.Dog;
import com.greenfoxacademy.dogs.models.entities.Toy;
import com.greenfoxacademy.dogs.models.exceptions.NoSuchDogException;
import com.greenfoxacademy.dogs.repositories.DogRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DogServiceImpl implements DogService {
  private final DogRepository repo;

  @Autowired
  public DogServiceImpl(DogRepository repo) {
    this.repo = repo;
  }

  @Override
  public List<Dog> getAll() {
    return repo.findAll();
  }

  @Override
  public void save(Dog dog) {
    repo.save(dog);
  }

  public Dog findById(String id) throws NoSuchDogException {
    if (!id.matches("[0-9]*") || !repo.existsById(Integer.parseInt(id))) {
      throw new NoSuchDogException();
    }
    return repo.findById(Integer.parseInt(id)).get();
  }

  @Override
  public void updateDog(Integer id, Dog dog) {
    dog.setId(id);
    repo.save(dog);
  }

  @Override
  public boolean dogValidation(Dog dog) throws IllegalArgumentException {
    if (dog.getName() == null || dog.getName().trim().isEmpty() || dog.getName().length() > 30) {
      throw new IllegalArgumentException("Please write a name which is not longer than 30 letters");
    }
    if (dog.getAge() == null) {
      throw new IllegalArgumentException("Please give us an age");
    }
    if (dog.getGender() == null) {
      throw new IllegalArgumentException("Please give us a gender");
    }
    if (dog.getLength() == null) {
      throw new IllegalArgumentException("Please give us a length");
    }
    if (dog.getHeight() == null) {
      throw new IllegalArgumentException("Please give us a height");
    }
    if (dog.getFavActivity() == null || dog.getFavActivity().trim().isEmpty()) {
      throw new IllegalArgumentException("Please give us a favourite activity");
    }
    if (dog.getDateOfBirth() == null) {
      throw new IllegalArgumentException("Please give us a birth date");
    }
    return true;
  }

  @Override
  public Dog addToy(Toy toy, String id) {
    Dog dog = repo.findById(Integer.parseInt(id)).get();
    dog.addToy(toy);
    repo.save(dog);
    return dog;
  }

  @Override
  public void deleteDog(String id) {
    repo.delete(repo.findById(Integer.parseInt(id)).get());
  }
}
