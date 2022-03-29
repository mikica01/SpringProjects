package com.greenfoxacademy.reddit.repositories;

import com.greenfoxacademy.reddit.models.User;
import com.greenfoxacademy.reddit.models.Post;
import com.greenfoxacademy.reddit.models.Vote;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface VoteRepo extends CrudRepository<Vote, Long> {
  public Vote findByUser(User user);
  public boolean existsByUserAndPost(User user, Post post);
  public boolean existsByUser(User user);
  public Vote findByUserAndPost(User user, Post post);
  @Query(value = "SELECT SUM(value) FROM votes WHERE post_id = ?1", nativeQuery = true)
  public Long sumVotesByPostId(Long postId);
}
