package com.greenfoxacademy.reddit.models.dtos;

import com.greenfoxacademy.reddit.models.Post;
import com.greenfoxacademy.reddit.models.User;
import java.time.Instant;

public class PostDTO {
  private Long id;
  private String title;
  private String url;
  private Long timestamp;
  private Long score;
  private UserDTO owner;
  private Integer vote;

  public PostDTO(String title, String url, Long timestamp) {
    this.title = title;
    this.url = url;
    this.timestamp = timestamp;
    this.score = 0L;
  }
  public PostDTO(Post post) {
    id = post.getId();
    title = post.getTitle();
    url = post.getUrl();
    timestamp = Instant.now().getEpochSecond();
    score = 0L;
    owner = new UserDTO(post.getOwner());
  }

  public UserDTO getOwner() {
    return owner;
  }

  public void setOwner(UserDTO owner) {
    this.owner = owner;
  }

  public Integer getVote() {
    return vote;
  }

  public void setVote(Integer vote) {
    this.vote = vote;
  }

  public void setOwner(User owner) {
    this.owner = new UserDTO(owner);
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

  public Long getScore() {
    return score;
  }

  public void setScore(Long score) {
    this.score = score;
  }
}

