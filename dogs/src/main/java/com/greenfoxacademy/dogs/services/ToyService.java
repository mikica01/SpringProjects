package com.greenfoxacademy.dogs.services;

import com.greenfoxacademy.dogs.models.entities.Toy;

public interface ToyService {
  void save(Toy toy);
  boolean toyValidation(Toy toy);
}
