package com.mikigyakorlas.todoapp.models.entities;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
@ApiModel("just a user")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty("user's id")
  private Integer id;
  @ApiModelProperty("user's name")
  private String name;
  @ApiModelProperty("user's email")
  private String email;
  @ApiModelProperty("user's password")
  private String password;
  @ApiModelProperty("user's gender")
  private String gender;
  @ApiModelProperty("user's bio")
  private String bio;
  @OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
  @ApiModelProperty("user's list of todos")
  private List<Todo> listOfTodos;

  public User(String name, String email, String password, String gender, String bio,
              List<Todo> listOfTodos) {
    this.name = name;
    this.email = email;
    this.password = password;
    this.gender = gender;
    this.bio = bio;
    this.listOfTodos = listOfTodos;
  }

  public User() {
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

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public String getBio() {
    return bio;
  }

  public void setBio(String bio) {
    this.bio = bio;
  }

  public List<Todo> getListOfTodos() {
    return listOfTodos;
  }

  public void setListOfTodos(List<Todo> listOfTodos) {
    this.listOfTodos = listOfTodos;
  }

  public void addTodo(Todo todo) {
    listOfTodos.add(todo);
    todo.setUser(this);
  }
}
