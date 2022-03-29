package com.greenfoxacademy.programmerfoxclub.repositories;

import com.greenfoxacademy.programmerfoxclub.models.Fox;
import java.util.List;


public interface FoxRepository {
  public List<Fox> getAllFoxes();
  public Fox getFoxByName(String name);
  public void saveFox(Fox fox);
}
