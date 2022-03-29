package com.greenfoxacademy.webshop.models;

public class ShopItem {
  private String name;
  private String description;
  private double price;
  private int quantityOfStock;
  private String type;
  private String currency;

  public ShopItem(String name, String description, double price, int quantityOfStock, String type) {
    this.name = name;
    this.description = description;
    this.price = price;
    this.quantityOfStock = quantityOfStock;
    this.type = type;
    this.currency = "Kč";
  }

  public String getCurrency() {
    return currency;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getDescription() {
    return description;
  }

  public double getPrice() {
    return price;
  }

  public int getQuantityOfStock() {
    return quantityOfStock;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public void setQuantityOfStock(int quantityOfStock) {
    this.quantityOfStock = quantityOfStock;
  }
}
