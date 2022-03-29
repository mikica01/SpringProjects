package com.mikigyakorlas.todoapp.models.dtos;

import com.mikigyakorlas.todoapp.models.enums.Priority;
import java.time.LocalDate;

public class TodoDTO {
  private Integer id;
  private String title;
  private String status;
  private LocalDate deadline;
  private Priority priority;
  private String username;

  public TodoDTO(Integer id, String title, String status, LocalDate deadline,
                 Priority priority, String username) {
    this.id = id;
    this.title = title;
    this.status = status;
    this.deadline = deadline;
    this.priority = priority;
    this.username = username;
  }

  public TodoDTO() {
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) {
    this.deadline = deadline;
  }

  public Priority getPriority() {
    return priority;
  }

  public void setPriority(Priority priority) {
    this.priority = priority;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
