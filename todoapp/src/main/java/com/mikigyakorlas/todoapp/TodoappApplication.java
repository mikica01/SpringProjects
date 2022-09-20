package com.mikigyakorlas.todoapp;

import com.mikigyakorlas.todoapp.models.entities.User;
import com.mikigyakorlas.todoapp.repositories.UserRepository;
import java.util.Collections;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class TodoappApplication {
  UserRepository repo;

  public TodoappApplication(UserRepository repo) {
    this.repo = repo;
  }

  public static void main(String[] args) {
    SpringApplication.run(TodoappApplication.class, args);
  }

  @Bean
  CommandLineRunner init() {
    return args -> repo.save(
        new User("Vörös Miklós", "mikica01@freemail.hu", "1993Szep15", "male", "Az én todoim",
            null));
  }

  @Bean
  public Docket swaggerConfig() {
    return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .build()
        .apiInfo(apiInfo());
  }
// főoldalon megjelenő szöveg
  private ApiInfo apiInfo() {
    return new ApiInfo(
        "Miki próba swagger",
        "próba",
        "1.0",
        "free to use",
        new springfox.documentation.service.Contact("Vörös Miklós", "https://github.com/mikica01",
            "v.miklos.gyt@gmail.com"),
        "API license",
        "https://github.com/mikica01",
        Collections.emptyList()
    );
  }
}
