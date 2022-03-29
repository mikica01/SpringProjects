package com.greenfoxacademy.webshop.service;

import com.greenfoxacademy.webshop.models.ShopItem;
import com.greenfoxacademy.webshop.repository.ItemRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemSortingService {
  ItemRepository repo;

  @Autowired
  public ItemSortingService(ItemRepository repo) {
    this.repo = repo;
  }

  public List<ShopItem> getAvailable() {
    List<ShopItem> sortedItems = repo.getItems();
    return sortedItems.stream()
        .filter(i -> i.getQuantityOfStock() > 0)
        .collect(Collectors.toList());
  }

  public List<ShopItem> ascending() {
    List<ShopItem> sortedItems = repo.getItems();
    return sortedItems.stream()
        .sorted((i1, i2) -> (int) (i1.getPrice() - i2.getPrice()))
        .collect(Collectors.toList());
  }

  public List<ShopItem> searchItems(String name) {
    List<ShopItem> list = repo.getItems();
    if (name == null || name.trim().isEmpty()) {
      return list;
    }
    return list.stream()
        .filter(x -> x.getName().toLowerCase().contains(name.toLowerCase()))
        .collect(Collectors.toList());
  }

  public String getAverage() {
    List<ShopItem> list = repo.getItems();
    if (list.isEmpty()) {
      return "0";
    }
    return "Average stock: " + list.stream()
        .map(ShopItem::getQuantityOfStock)
        .mapToInt(Integer::intValue)
        .average()
        .getAsDouble();
  }

  public String mostExpensive() {
    List<ShopItem> items = repo.getItems();
    ShopItem mostExpensive = items.stream()
        .sorted((i1, i2) -> (int) (i2.getPrice() - i1.getPrice()))
        .findFirst()
        .get();
    return "The most expensive item in shop is: " + mostExpensive.getName() + ", it costs " +
        mostExpensive.getPrice() + " Kč";
  }

  public List<ShopItem> filterByType(String type) {
    List<ShopItem> items = repo.getItems();
    switch (type) {
      case "1":
        return items.stream()
            .filter(x -> x.getType().equals("Clothes and Shoes"))
            .collect(Collectors.toList());
      case "2":
        return items.stream()
            .filter(x -> x.getType().equals("Electronics"))
            .collect(Collectors.toList());
      case "3":
        return items.stream()
            .filter(x -> x.getType().equals("Beverages and Snacks"))
            .collect(Collectors.toList());
      case "4":
        return items.stream()
            .filter(x -> x.getDescription().toLowerCase().contains("nike"))
            .collect(Collectors.toList());
    }
    return items;
  }

  public List<ShopItem> priceInEur() {
    List<ShopItem> items = repo.getItems();
    if (items.get(0).getCurrency().equals("€")) {
      return items;
    }
    for (ShopItem item : items) {
      item.setPrice(Double.parseDouble(String.format("%.3f", item.getPrice() * 0.041)));
      item.setCurrency("€");
    }
    return items;
  }

  public List<ShopItem> originalCurrency() {
    List<ShopItem> items = repo.getItems();
    if (items.get(0).getCurrency().equals("Kč")) {
      return items;
    }
    return items.stream()
        .map(x -> {
          x.setPrice(Double.parseDouble(String.format("%.3f", x.getPrice() / 0.041)));
          x.setCurrency("Kč");
          return x;
        })
        .collect(Collectors.toList());
//    for (ShopItem item : items) {
//      item.setPrice(Double.parseDouble(String.format("%.3f", item.getPrice() / 0.041)));
//      item.setCurrency("Kč");
//    }
//    return items;
  }

  public List<ShopItem> filterByPrice(Integer number, String action) {
    List<ShopItem> items = repo.getItems();
    if (null == number) {
      return items;
    } else if (action.equals("Above")) {
      return items.stream()
          .filter(x -> x.getPrice() > (double) number)
          .collect(Collectors.toList());
    } else if (action.equals("Below")) {
      return items.stream()
          .filter(x -> x.getPrice() < (double) number)
          .collect(Collectors.toList());
    }
    return items.stream()
        .filter(x -> x.getPrice() == (double) number)
        .collect(Collectors.toList());
  }
}
