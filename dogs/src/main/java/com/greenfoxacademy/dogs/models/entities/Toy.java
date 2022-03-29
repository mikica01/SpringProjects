package com.greenfoxacademy.dogs.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "toys")
public class Toy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  @ManyToOne
  @JsonIgnore
  private Dog owner;

  public Toy(String name, Dog owner) {
    this.name = name;
    this.owner = owner;
  }

  public Toy() {
  }

  public Integer getId() {
    return id;
  }

  public Dog getOwner() {
    return owner;
  }

  public void setOwner(Dog owner) {
    this.owner = owner;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
