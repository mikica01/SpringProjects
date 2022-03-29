package com.greenfoxacademy.programmerfoxclub.controllers;

import com.greenfoxacademy.programmerfoxclub.services.FoxService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NutritionStoreController {
  FoxService service;

  @Autowired
  public NutritionStoreController(
      FoxService service) {
    this.service = service;
  }

  @PostMapping("/setBeverages")
  public String setBeverages(Model model, @RequestParam String name, @RequestParam(required = false) String drink,
                             @RequestParam(required = false) String food) {
    model.addAttribute("fox", service.setBeverages(name, food, drink));
    LocalDateTime now = LocalDateTime.now();
    service.addModification(
        String.format(
            "%d-%02d-%02d %02d:%02d:%d The favourite food and drink of %s set to %s and %s.",
            now.getYear(),
            now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute(),
            now.getSecond(), name,
            food, drink), name);
    return "redirect:/?name=" + name;
  }

  @GetMapping("/trickCenter")
  public String trickCenter(Model model, @RequestParam String name) {
    model.addAttribute("fox", service.getFoxByItsName(name));
    model.addAttribute("hidden", "hidden");
    model.addAttribute("tricksVisible", true);
    model.addAttribute("allTheTricks", true);
    return "main";
  }

  @PostMapping("/trickCenter")
  public String addTrick(@RequestParam String trick,
                         @RequestParam String redirectName) {
    LocalDateTime now = LocalDateTime.now();
    service.addModification(
        String.format("%d-%02d-%02d %02d:%02d:%d %s has now earned a new skill: %s.", now.getYear(),
            now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute(),
            now.getSecond(), redirectName, trick),
        redirectName);

    service.addTrick(trick, redirectName);
    return "redirect:/trickCenter?name=" + redirectName + "&?trick=" + trick;
  }
}
