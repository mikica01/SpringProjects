package com.greenfoxacademy.programmerfoxclub;

import com.greenfoxacademy.programmerfoxclub.repositories.FoxInMemoryRepository;
import com.greenfoxacademy.programmerfoxclub.repositories.FoxRepository;
import com.greenfoxacademy.programmerfoxclub.services.FoxService;
import com.greenfoxacademy.programmerfoxclub.services.ProgrammerFoxService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class foxConfig {

//  @Bean
//  public FoxRepository configRepo() {
//    return new FoxInMemoryRepository();
//  }

  @Bean
  public FoxService configService() {
    return new ProgrammerFoxService(new FoxInMemoryRepository());
  }
}
