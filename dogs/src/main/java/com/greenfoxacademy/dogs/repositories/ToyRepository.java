package com.greenfoxacademy.dogs.repositories;

import com.greenfoxacademy.dogs.models.entities.Toy;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ToyRepository extends CrudRepository<Toy, Integer> {
}
