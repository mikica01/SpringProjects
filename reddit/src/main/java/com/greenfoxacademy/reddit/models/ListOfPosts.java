package com.greenfoxacademy.reddit.models;

import com.greenfoxacademy.reddit.models.dtos.PostDTO;
import java.util.ArrayList;
import java.util.List;

public class ListOfPosts {
  private List<PostDTO> posts;

  public ListOfPosts() {
    posts = new ArrayList<>();
  }

  public List<PostDTO> getPosts() {
    return posts;
  }

  public void setPosts(List<PostDTO> posts) {
    this.posts = posts;
  }
}
