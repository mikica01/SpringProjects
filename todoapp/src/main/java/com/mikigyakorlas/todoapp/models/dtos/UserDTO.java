package com.mikigyakorlas.todoapp.models.dtos;

import java.util.List;

public class UserDTO {
  private String name;
  private String email;
  private String gender;
  private String bio;
  private List<TodoDTO> todos;

  public UserDTO(String name, String email, String gender,String bio,
                 List<TodoDTO> todos) {
    this.name = name;
    this.email = email;
    this.gender = gender;
    this.bio = bio;
    this.todos = todos;
  }

  public UserDTO() {
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

  public List<TodoDTO> getTodos() {
    return todos;
  }

  public void setTodos(List<TodoDTO> todos) {
    this.todos = todos;
  }
}
