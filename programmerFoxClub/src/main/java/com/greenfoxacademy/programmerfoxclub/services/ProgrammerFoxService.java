package com.greenfoxacademy.programmerfoxclub.services;

import com.greenfoxacademy.programmerfoxclub.models.Fox;
import com.greenfoxacademy.programmerfoxclub.repositories.FoxRepository;
import java.util.List;

public class ProgrammerFoxService implements FoxService {
  FoxRepository repo;

  public ProgrammerFoxService(FoxRepository repo) {
    this.repo = repo;
  }

  public List<Fox> getFoxes() {
    return repo.getAllFoxes();
  }

  public Fox getFoxByItsName(String name) {
    return repo.getFoxByName(name);
  }

  public Fox setBeverages(String name, String food, String drink) {
    return getFoxes().stream()
        .filter(f -> f.getName().equals(name))
        .map(f -> {
          f.setFood(food);
          f.setDrink(drink);
          return f;
        }).findFirst().orElse(new Fox());
  }

  public void addTrick(String trick, String name) {
    for (Fox fox : getFoxes()) {
      if (fox.getName().equals(name) && !fox.getListOfTricks().contains(trick)) {
        fox.addTrick(trick);
      }
    }
  }

  public void addModification(String modification, String name) {
   getFoxes().stream().filter(x-> x.getName().equals(name)).findFirst().get().addModification(modification);
//    for (Fox fox : getFoxes()) {
//      if (fox.getName().equals(name)) {
//        fox.addModification(modification);
//      }
//    }
  }

  public void addFox(Fox fox) {
    repo.saveFox(fox);
  }
}
