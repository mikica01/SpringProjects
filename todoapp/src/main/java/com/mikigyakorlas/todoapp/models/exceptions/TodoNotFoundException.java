package com.mikigyakorlas.todoapp.models.exceptions;

public class TodoNotFoundException extends RuntimeException{
  public TodoNotFoundException() {
    super("Todo not found by this id");
  }
}
