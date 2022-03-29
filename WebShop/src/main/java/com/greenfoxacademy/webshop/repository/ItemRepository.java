package com.greenfoxacademy.webshop.repository;

import com.greenfoxacademy.webshop.models.ShopItem;
import java.util.Arrays;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

  private static List<ShopItem> itemList = Arrays.asList(
      new ShopItem("Running Shoes", "Nike running shoes for every day sport", 1000.0, 5, "Clothes and Shoes"),
      new ShopItem("Printer", "Some HP printer that will print pages", 3000.0, 2, "Electronics"),
      new ShopItem("Coca cola", "0.5l standard coke", 25.0, 0, "Beverages and Snacks"),
      new ShopItem("Wokin", "Chicken with fried rice and WOKIN sauce", 119.0, 100, "Beverages and Snacks"),
      new ShopItem("T-shirt", "Blue with a corgi on a bike", 300.0, 1, "Clothes and Shoes")
  );


  public static List<ShopItem> getItems() {
    return itemList;
  }
}
