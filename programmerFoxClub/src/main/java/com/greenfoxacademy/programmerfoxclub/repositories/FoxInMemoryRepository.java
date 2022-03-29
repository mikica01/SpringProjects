package com.greenfoxacademy.programmerfoxclub.repositories;

import com.greenfoxacademy.programmerfoxclub.models.Fox;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FoxInMemoryRepository implements FoxRepository {
  private List<Fox> listOfProgrammerFoxes = new ArrayList<>(
      Arrays.asList(new Fox("Pista", "Rétes", "Málnaszörp", "male"),
          new Fox("Béla", "Pesztóstészta", "Ázsványvíz", "male"),
          new Fox("Józsi", "Hávájpizza", "Kóla", "male"),
          new Fox("Mari", "Tepertőspogácsa", "Viszkikóla", "female"),
          new Fox("Bözse", "Tőtöttkáposzta", "Tőtöttkáposztalé","female"),
          new Fox("Örzse", "Nyúlhús", "Ablaktisztittó folyadék", "female")));

  @Override
  public List<Fox> getAllFoxes() {
    return listOfProgrammerFoxes;
  }

  @Override
  public Fox getFoxByName(String name) {
    return getAllFoxes().stream()
        .filter(fox -> fox.getName().equalsIgnoreCase(name))
        .findFirst()
        .orElse(null);
  }
  public void saveFox(Fox fox) {
    listOfProgrammerFoxes.add(fox);
  }
}
