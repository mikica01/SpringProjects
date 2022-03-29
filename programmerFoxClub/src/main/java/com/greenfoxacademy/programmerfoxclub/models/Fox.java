package com.greenfoxacademy.programmerfoxclub.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Fox {
  private String name;
  private List<String> listOfTricks;
  private String food;
  private String drink;
  private String gender;
  private List<String> listOfModifies;

  public Fox(String name, String food, String drink, String gender) {
    this.name = name;
    this.food = food;
    this.drink = drink;
    this.gender = gender;
    listOfTricks = new LinkedList<>();
    listOfModifies = new ArrayList<>();
  }
  public Fox() {
    listOfTricks = new LinkedList<>();
    listOfModifies = new ArrayList<>();
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<String> getListOfTricks() {
    return listOfTricks;
  }

  public void addTrick(String trick) {
    listOfTricks.add(trick);
  }

  public String getFood() {
    return food;
  }

  public void setFood(String food) {
    this.food = food;
  }

  public String getDrink() {
    return drink;
  }

  public void setDrink(String drink) {
    this.drink = drink;
  }
  public void addModification(String modification) {
    listOfModifies.add(modification);
  }

  public List<String> getListOfModifies() {
    return listOfModifies;
  }

}
