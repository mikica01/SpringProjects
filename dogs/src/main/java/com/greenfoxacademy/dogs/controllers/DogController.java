package com.greenfoxacademy.dogs.controllers;

import com.greenfoxacademy.dogs.models.entities.Dog;
import com.greenfoxacademy.dogs.models.exceptions.NoSuchDogException;
import com.greenfoxacademy.dogs.services.DogService;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DogController {
  private final DogService service;

  @Autowired
  public DogController(DogService service) {
    this.service = service;
  }

  @ModelAttribute
  public void addAll(Model model) {
    model.addAttribute("dogs", service.getAll());
    model.addAttribute("listOfTypes",
        Arrays.asList("Golden Retriever", "Commodore", "German Shepherd", "Pug", "Boxer"));
  }

  @GetMapping("/")
  public String renderMainPage() {
    return "main";
  }

  @GetMapping("/add")
  public String addDog() {
    return "form";
  }

  @GetMapping("/edit/{id}")
  public String renderEditDog(@PathVariable String id, Model model) {
    try {
      service.findById(id);
    } catch (NoSuchDogException e) {
      model.addAttribute("error", e.getMessage());
      return "main";
    }
    model.addAttribute("dog", service.findById(id));
    return "form";
  }

  @PostMapping("/edit/{id}")
  public String editDog(@PathVariable String id, @ModelAttribute Dog dog, Model model) {
    try {
      service.findById(id);
      service.dogValidation(dog);
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      return "form";
    }
    service.updateDog(Integer.parseInt(id), dog);
    return "redirect:/";
  }

    @PostMapping("/add")
  public String addDog(@ModelAttribute Dog dog, Model model) {
    try {
      service.dogValidation(dog);
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      model.addAttribute("dog", null);
      return "form";
    }
    service.save(dog);
    return "redirect:/";
  }

  @PostMapping("/delete/{id}")
  public String deleteDog(@PathVariable String id, Model model) {
    try {
      service.findById(id);
    } catch (NoSuchDogException | IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      return "main";
    }
    service.deleteDog(id);
    return "redirect:/";
  }
}
