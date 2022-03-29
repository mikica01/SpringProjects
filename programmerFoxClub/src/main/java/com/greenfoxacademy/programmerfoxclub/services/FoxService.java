package com.greenfoxacademy.programmerfoxclub.services;

import com.greenfoxacademy.programmerfoxclub.models.Fox;
import java.util.List;

public interface FoxService {
  public List<Fox> getFoxes();
  public Fox getFoxByItsName(String name);
  public Fox setBeverages(String name, String food, String drink);
  public void addTrick(String trick, String name);
  public void addModification(String modification, String name);
  public void addFox(Fox fox);
}
