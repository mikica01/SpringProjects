package com.greenfoxacademy.reddit.models;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity(name = "owners")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  @OneToMany(mappedBy = "owner")
  private List<Post> listOfPosts;
  @OneToMany(mappedBy = "user")
  private List<Vote> listOfVotes;

  public User(String name) {
    this.name = name;
    listOfPosts = new ArrayList<>();
    listOfVotes = new ArrayList<>();
  }

  public User() {
    listOfPosts = new ArrayList<>();
    listOfVotes = new ArrayList<>();
  }
  public List<Vote> getListOfVotes() {
    return listOfVotes;
  }

  public void setListOfVotes(List<Vote> listOfVotes) {
    this.listOfVotes = listOfVotes;
  }


  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Post> getListOfPosts() {
    return listOfPosts;
  }

  public void setListOfPosts(List<Post> listOfPosts) {
    this.listOfPosts = listOfPosts;
  }
}
