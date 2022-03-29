package com.mikigyakorlas.todoapp.models.exceptions;

public class UserNotFoundException extends RuntimeException{
  public UserNotFoundException() {
    super("User not found by this id");
  }
}
