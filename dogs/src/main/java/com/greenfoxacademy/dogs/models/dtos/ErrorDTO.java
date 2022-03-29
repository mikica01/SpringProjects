package com.greenfoxacademy.dogs.models.dtos;

public class ErrorDTO {
  String error;

  public ErrorDTO(String error) {
    this.error = error;
  }

  public ErrorDTO() {
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }
}
