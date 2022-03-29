package com.greenfoxacademy.reddit.models;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity(name = "posts")
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String url;
  private Long timestamp;
  @ManyToOne
  private User owner;
  @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
  private List<Vote> votes;

  public Post(String title, String url, User owner) {
    this.title = title;
    this.url = url;
    this.owner = owner;
    this.timestamp = Instant.now().getEpochSecond();
    votes = new ArrayList<>();
  }

  public Post() {
    this.timestamp = Instant.now().getEpochSecond();
    votes = new ArrayList<>();
  }

  public List<Vote> getVotes() {
    return votes;
  }

  public void setVotes(List<Vote> votes) {
    this.votes = votes;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Long getTimestamp() {
    return timestamp;
  }

  public void setTimestamp(Long timestamp) {
    this.timestamp = timestamp;
  }

}
