package com.greenfoxacademy.dogs.services;

import com.greenfoxacademy.dogs.models.entities.Toy;
import com.greenfoxacademy.dogs.repositories.ToyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToyServiceImpl implements ToyService {
  ToyRepository repo;

  @Autowired
  public ToyServiceImpl(ToyRepository repo) {
    this.repo = repo;
  }

  @Override
  public void save(Toy toy) {
    repo.save(toy);
  }

  @Override
  public boolean toyValidation(Toy toy) throws IllegalArgumentException {
    if (toy.getName() == null || toy.getName().trim().isEmpty()) {
      throw new IllegalArgumentException("You must give a name");
    }
    return true;
  }
}
