package com.greenfoxacademy.reddit.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity(name = "votes")
public class Vote {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;
  Integer value = 0;
  @ManyToOne
  User user;
  @ManyToOne
  Post post;

  public Vote(User user, Post post) {
    this.user = user;
    this.post = post;
  }

  public Vote() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Integer getValue() {
    return value;
  }

  public void setValue(Integer value) {
    this.value = value;
  }

  public User getUser() {
    return user;
  }

  public void setOwner(User user) {
    this.user = user;
  }

  public Post getPost() {
    return post;
  }

  public void setPost(Post post) {
    this.post = post;
  }
}
