package com.greenfoxacademy.reddit;

import com.greenfoxacademy.reddit.models.User;
import com.greenfoxacademy.reddit.models.Post;
import com.greenfoxacademy.reddit.repositories.OwnerRepo;
import com.greenfoxacademy.reddit.repositories.RedditRepo;
import java.util.Arrays;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RedditApplication implements CommandLineRunner {

  RedditRepo postRepo;
  OwnerRepo ownerRepo;

  public RedditApplication(RedditRepo postRepo, OwnerRepo ownerRepo) {
    this.postRepo = postRepo;
    this.ownerRepo = ownerRepo;
  }

  public static void main(String[] args) {
    SpringApplication.run(RedditApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    User joska = new User("Jóska");
    User pista = new User("Pista");
    User bela = new User("Bela");
    User mari = new User("Mari");
    User bozse = new User("Bözse");
    User erzso = new User("Erzsó");
    Post first = new Post("valami", "valami", joska);
    Post second = new Post("valami", "valami", joska);
    Post third = new Post("valami", "valami", bela);
    Post fourth = new Post("valami", "valami", mari);
    Post fifth = new Post("valami", "valami", erzso);
    Post sixth = new Post("valami", "valami", erzso);


    ownerRepo.saveAll(Arrays.asList(joska, pista, bela, mari, bozse, erzso));
    postRepo.saveAll(Arrays.asList(first, second, third, fourth, fifth, sixth));

  }
}
