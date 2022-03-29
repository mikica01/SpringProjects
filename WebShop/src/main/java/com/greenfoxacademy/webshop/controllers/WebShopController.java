package com.greenfoxacademy.webshop.controllers;

import com.greenfoxacademy.webshop.repository.ItemRepository;
import com.greenfoxacademy.webshop.service.ItemSortingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class WebShopController {
  ItemSortingService service;

  @Autowired
  public WebShopController(ItemSortingService service) {
    this.service = service;
  }

  @GetMapping("/webshop")
  public String mainPage(Model model) {
    model.addAttribute("itemList", ItemRepository.getItems());
    return "main";
  }

  @PostMapping("/webshop")
  public String searchEngine(Model model, @RequestParam(required = false) String search) {
    model.addAttribute("itemList", service.searchItems(search));
    return "main";
  }

  @GetMapping("/only-available")
  public String renderOnlyAvailable(Model model) {
    model.addAttribute("itemList", service.getAvailable());
    return "main";
  }

  @GetMapping("/cheapest-first")
  public String ascendingOrder(Model model) {
    model.addAttribute("itemList", service.ascending());
    return "main";
  }

  @GetMapping("/average-stock")
  public String averageStock(Model model) {
    model.addAttribute("average", service.getAverage());
    model.addAttribute("hidden", "hidden");
    return "main";
  }

  @GetMapping("/most-expensive")
  public String mostExpensive(Model model) {
    model.addAttribute("mostExpensive", service.mostExpensive());
    model.addAttribute("hidden", "hidden");
    return "main";
  }

  @GetMapping("/more-filters")
  public String moreFilters(Model model) {
    model.addAttribute("itemList", ItemRepository.getItems());
    return "more";
  }

  @GetMapping("/filter-by-type/{type}")
  public String filterByType(Model model, @PathVariable String type) {
    model.addAttribute("itemList", service.filterByType(type));
    return "more";
  }

  @GetMapping("/price-in-eur")
  public String priceInEur(Model model) {
    model.addAttribute("itemList", service.priceInEur());
    return "more";
  }

  @GetMapping("/price-in-original")
  public String priceInOriginal(Model model) {
    model.addAttribute("itemList", service.originalCurrency());
    return "more";
  }

  @PostMapping("/filter-by-price")
  public String filterByPrice(Model model, @RequestParam(required = false) Integer number,
                              @RequestParam(required = false) String action) {
    model.addAttribute("itemList", service.filterByPrice(number, action));
    return "more";
  }
}
