package com.greenfoxacademy.programmerfoxclub.controllers;

import com.greenfoxacademy.programmerfoxclub.models.Fox;
import com.greenfoxacademy.programmerfoxclub.services.FoxService;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
  FoxService service;

  @Autowired
  public MainController(
      FoxService service) {
    this.service = service;
  }

  @GetMapping("/")
  public String renderProfilePage(Model model, @RequestParam String name) {
    if (service.getFoxByItsName(name) == null) {
      model.addAttribute("newAccount",
          "Egy eddig még nem használt nevet adtál meg, készíts vele új rókát!");
      model.addAttribute("signup",
          "Sign up a new fox");

      return "login";
    }
    model.addAttribute("fox", service.getFoxByItsName(name));
    return "main";
  }

  @GetMapping("/login")
  public String renderLoginPage() {
    return "login";
  }

  @PostMapping("/login")
  public String loginInput(@RequestParam String name) {
    if (name == null || name.trim().isEmpty()) {
      return "redirect:/login";
    }
    if (service.getFoxByItsName(name) != null) {
      LocalDateTime now = LocalDateTime.now();
      service.addModification(
          String.format("%d-%02d-%02d %02d:%02d:%02d %s has logged in.", now.getYear(),
              now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute(),
              now.getSecond(), name), name);
      return "redirect:/?name=" + name;
    }
    return "redirect:/?name=" + name;
  }

  @GetMapping("/nutritionStore")
  public String nutritionStore(Model model, @RequestParam String name) {
    model.addAttribute("fox", service.getFoxByItsName(name));
    model.addAttribute("hidden", "hidden");
    model.addAttribute("beverageVisible", true);
    return "main";
  }

  @GetMapping("/actionHistory")
  public String actionHistory(Model model, @RequestParam String name) {
    model.addAttribute("fox", service.getFoxByItsName(name));
    model.addAttribute("hidden", "hidden");
    model.addAttribute("modificationList", service.getFoxByItsName(name).getListOfModifies());
    model.addAttribute("historyVisible", true);
    return "main";
  }

  @PostMapping("/signUp")
  public String signUp(@ModelAttribute Fox fox) {
    service.addFox(fox);
    return "redirect:/login";
  }

  @GetMapping("/signUp")
  public String signUp(Model model) {
    model.addAttribute("signUpVisible", true);
    model.addAttribute("loginVisible", true);
    return "login";
  }
}
