package com.greenfoxacademy.dogs.controllers;

import com.greenfoxacademy.dogs.models.dtos.ErrorDTO;
import com.greenfoxacademy.dogs.models.entities.Toy;
import com.greenfoxacademy.dogs.models.exceptions.NoSuchDogException;
import com.greenfoxacademy.dogs.services.DogService;
import com.greenfoxacademy.dogs.services.ToyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DogRestController {

  private final DogService dogService;
  private final ToyService toyService;

  @Autowired
  public DogRestController(DogService dogService,
                           ToyService toyService) {
    this.dogService = dogService;
    this.toyService = toyService;
  }

  @GetMapping("/api/dogs")
  public ResponseEntity<?> listAllDogs() {
    return ResponseEntity.ok(dogService.getAll());
  }

  @PostMapping("/api/dogs/{id}")
  public ResponseEntity<?> addToy(@PathVariable String id, @RequestBody Toy toy) {
    try {
      dogService.findById(id);
      toyService.toyValidation(toy);
    } catch (NoSuchDogException e) {
      return ResponseEntity.status(404).body(new ErrorDTO(e.getMessage()));
    } catch (IllegalArgumentException e) {
      return ResponseEntity.status(403).body(new ErrorDTO(e.getMessage()));
    }
    return ResponseEntity.ok(dogService.addToy(toy, id));
  }
}
