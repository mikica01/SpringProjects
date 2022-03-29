package com.mikigyakorlas.todoapp.models.entities;

import com.mikigyakorlas.todoapp.models.enums.Priority;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import org.springframework.format.annotation.DateTimeFormat;

@Entity(name = "todoz")
public class Todo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;
  private String title;
  private String status;
  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate deadline;
  private Priority priority;
  private LocalDate createdAt;
  private LocalDate modifiedAt;
  @ManyToOne
  private User user;

  public Todo(String title, LocalDate deadline,
              Priority priority,
              User user) {
    this.title = title;
    this.status = "undone";
    this.deadline = deadline;
    this.priority = priority;
    this.user = user;
  }

  public Todo() {
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
  public void setPriority(String priority) {
    this.priority = Priority.valueOf(priority);
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public LocalDate getModifiedAt() {
    return modifiedAt;
  }

  public void setModifiedAt(LocalDate modifiedAt) {
    this.modifiedAt = modifiedAt;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }
}
