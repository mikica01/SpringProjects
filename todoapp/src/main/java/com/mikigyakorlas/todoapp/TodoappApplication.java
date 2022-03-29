package com.mikigyakorlas.todoapp;

import com.mikigyakorlas.todoapp.models.entities.User;
import com.mikigyakorlas.todoapp.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodoappApplication implements CommandLineRunner {
UserRepository repo;

  public TodoappApplication(UserRepository repo) {
    this.repo = repo;
  }

  public static void main(String[] args) {
    SpringApplication.run(TodoappApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    repo.save(new User("Vörös Miklós", "mikica01@freemail.hu", "1993Szep15", "male", "Az én todoim", null));
  }
}
