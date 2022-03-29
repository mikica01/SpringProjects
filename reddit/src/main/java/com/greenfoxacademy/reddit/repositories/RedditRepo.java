package com.greenfoxacademy.reddit.repositories;

import com.greenfoxacademy.reddit.models.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedditRepo extends CrudRepository<Post, Long> {
  @Override
  public List<Post> findAll();
  @Override
  public Optional<Post> findById(Long id);
  public void deleteById(Long id);
}
