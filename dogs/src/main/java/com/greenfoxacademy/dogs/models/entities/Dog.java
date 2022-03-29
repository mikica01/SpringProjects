package com.greenfoxacademy.dogs.models.entities;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "dogs")
public class Dog {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String name;
  @Transient
  private Integer age;
  private String gender;
  private String type;
  private Double length;
  private Double height;
  @Column(name = "favourite_activity")
  private String favActivity;
  @Column(name = "date_of_birth")
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate dateOfBirth;
  @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
  private List<Toy> listOfToys;

  public Dog() {
    listOfToys = new ArrayList<>();
  }

  public Dog(String name, String gender, String type, Double length,
             Double height, String favActivity, LocalDate dateOfBirth) {
    this.name = name;
    this.gender = gender;
    this.type = type;
    this.length = length;
    this.height = height;
    this.favActivity = favActivity;
    this.dateOfBirth = dateOfBirth;
    listOfToys = new ArrayList<>();
  }

  public Integer getId() {
    return id;
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

  public Integer getAge() {
    return Period.between(this.dateOfBirth, LocalDate.now()).getYears();
  }

  public void setAge(Integer age) {
    this.age = age;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Double getLength() {
    return length;
  }

  public void setLength(Double length) {
    this.length = length;
  }

  public Double getHeight() {
    return height;
  }

  public void setHeight(Double height) {
    this.height = height;
  }

  public String getFavActivity() {
    return favActivity;
  }

  public void setFavActivity(String favActivity) {
    this.favActivity = favActivity;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public List<Toy> getListOfToys() {
    return listOfToys;
  }

  public void setListOfToys(List<Toy> listOfToys) {
    this.listOfToys = listOfToys;
    listOfToys.forEach(x -> x.setOwner(this));
  }
  public void addToy(Toy toy) {
    toy.setOwner(this);
    listOfToys.add(toy);
  }
}
