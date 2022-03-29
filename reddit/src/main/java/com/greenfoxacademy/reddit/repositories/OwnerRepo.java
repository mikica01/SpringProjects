package com.greenfoxacademy.reddit.repositories;

import com.greenfoxacademy.reddit.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerRepo extends CrudRepository<User, Long> {
  public User findByName(String name);
}
