package com.greenfoxacademy.reddit.services;

import com.greenfoxacademy.reddit.models.ErrorMessage;
import com.greenfoxacademy.reddit.models.ListOfPosts;
import com.greenfoxacademy.reddit.models.User;
import com.greenfoxacademy.reddit.models.Post;
import com.greenfoxacademy.reddit.models.Vote;
import com.greenfoxacademy.reddit.models.dtos.PostDTO;
import com.greenfoxacademy.reddit.repositories.OwnerRepo;
import com.greenfoxacademy.reddit.repositories.RedditRepo;
import com.greenfoxacademy.reddit.repositories.VoteRepo;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class RedditService {
  RedditRepo postRepo;
  VoteRepo voteRepo;
  OwnerRepo userRepo;

  public RedditService(RedditRepo postRepo, VoteRepo voteRepo, OwnerRepo userRepo) {
    this.postRepo = postRepo;
    this.voteRepo = voteRepo;
    this.userRepo = userRepo;
  }

  public ListOfPosts getAllPosts(String username) {
    List<Post> postList = postRepo.findAll();
    ListOfPosts posts = new ListOfPosts();
    for (Post post : postList) {
        posts.getPosts().add(convert(post, username));
    }

    return posts;
  }

  public void save(Post post, String username) {
    if (post.getOwner() == null && username != null) post.setOwner(userRepo.findByName(username));
    postRepo.save(post);
  }

  public PostDTO upvote(Long id, String username) {
    return vote(id, username, 1);

  }

  public PostDTO downVote(Long id, String username) {
    return vote(id, username, -1);
  }

  public User findUserByName(String name) {
    if (userRepo.findByName(name) == null) {
      return null;
    }
    return userRepo.findByName(name);
  }

  public Post findPostById(Long id) {
    if (!postRepo.findById(id).isPresent()) {
      return null;
    }
    return postRepo.findById(id).get();
  }

  public PostDTO modifyPost(Long id, Post post, String username) {
    Post originalPost = postRepo.findById(id).get();
    if (post.getTitle() != null) {
      originalPost.setTitle(post.getTitle());
    }
    if (post.getUrl() != null) {
      originalPost.setUrl(post.getUrl());
    }
    return convert(postRepo.save(originalPost), username);
  }

  public PostDTO deletePost(Long id, String username) {
    Post beingDeletedPost = postRepo.findById(id).get();
    postRepo.deleteById(id);
    return convert(beingDeletedPost, username);
  }

  public PostDTO convert(Post post, String username) {
    PostDTO newPost = new PostDTO(post.getTitle(), post.getUrl(), post.getTimestamp());
    newPost.setId(post.getId());
    newPost.setScore(voteRepo.sumVotesByPostId(post.getId()));
    Vote vote = voteRepo.findByUserAndPost(userRepo.findByName(username), post);
    if (vote != null) {
      newPost.setVote(vote.getValue());
    }
    newPost.setOwner(post.getOwner());
    return newPost;
  }
  public PostDTO vote(Long id, String username, int value) {
    Post post = postRepo.findById(id).get();
    User user = userRepo.findByName(username);
    Vote vote = voteRepo.findByUserAndPost(user, post);
    if (vote == null) {
      vote = new Vote(user, post);
      post.getVotes().add(vote);
    }
    vote.setValue(value);
    postRepo.save(post);
    return convert(post, username);
  }
  public ErrorMessage validation(Long id, String username) {
    if (id <= 0) {
      return new ErrorMessage("you must write an id that is higher than 0");
    }
    if (findUserByName(username) == null) {
      return new ErrorMessage("no voter found by this name");
    } else if (findPostById(id) == null) {
      return new ErrorMessage("post not found by this id");
    }
    return null;
  }
}


