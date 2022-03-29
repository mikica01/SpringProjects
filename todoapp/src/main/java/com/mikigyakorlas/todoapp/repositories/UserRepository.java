package com.mikigyakorlas.todoapp.repositories;

import com.mikigyakorlas.todoapp.models.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
  boolean existsByEmail(String email);
  boolean existsByName(String name);
  User findByName(String name);
}
