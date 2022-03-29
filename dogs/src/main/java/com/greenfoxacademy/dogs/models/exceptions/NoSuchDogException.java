package com.greenfoxacademy.dogs.models.exceptions;

public class NoSuchDogException extends RuntimeException {
  public NoSuchDogException() {
    super("There is no such dog!");
  }
}
