package com.greenfoxacademy.reddit.controllers;

import com.greenfoxacademy.reddit.models.ErrorMessage;
import com.greenfoxacademy.reddit.models.Post;
import com.greenfoxacademy.reddit.models.dtos.PostDTO;
import com.greenfoxacademy.reddit.services.RedditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RedditController {
  RedditService service;

  @Autowired
  public RedditController(RedditService service) {
    this.service = service;
  }

  @GetMapping("/posts")
  public ResponseEntity<?> getPost(@RequestHeader(required = false) String username) {
    return ResponseEntity.status(200).body(service.getAllPosts(username));
  }

  @PostMapping("/posts")
  public ResponseEntity<?> createPost(@RequestBody Post post, @RequestHeader (required = false) String username) {
    service.save(post, username);
    return ResponseEntity.ok(new PostDTO(post));
  }

  @PutMapping("/posts/{id}/upvote")
  public ResponseEntity<?> upvotePost(@PathVariable Long id,
                                      @RequestHeader(required = false) String username) {
    ErrorMessage error = service.validation(id, username);
    if (error == null) {
      return ResponseEntity.status(200).body(service.upvote(id, username));
    }
    return ResponseEntity.status(404).body(error);
  }

  @PutMapping("/posts/{id}/downvote")
  public ResponseEntity<?> downVotePost(@PathVariable Long id,
                                        @RequestHeader(required = false) String username) {
    ErrorMessage error = service.validation(id, username);
    if (error == null) {
      return ResponseEntity.status(200).body(service.downVote(id, username));
    }
    return ResponseEntity.status(404).body(error);
  }

  @PutMapping("/posts/{id}")
  public ResponseEntity<?> modifyPost(@PathVariable Long id, @RequestBody Post post,
                                      @RequestHeader String username) {
    ErrorMessage error = service.validation(id, username);
    if (error == null) {
      return ResponseEntity.status(200).body(service.modifyPost(id, post, username));
    }
    return ResponseEntity.status(404).body(error);
  }

  @DeleteMapping("posts/{id}")
  public ResponseEntity<?> deletePost(@PathVariable Long id, @RequestHeader String username) {
    ErrorMessage error = service.validation(id, username);
    if (error == null) {
      return ResponseEntity.status(200).body(service.deletePost(id, username));
    }
    return ResponseEntity.status(404).body(error);
  }
}
