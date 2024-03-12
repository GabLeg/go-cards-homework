package com.example.gocardshomework.config;

import com.example.gocardshomework.domain.game.Game;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class AppConfiguration {

  @Bean
  public Map<String, Game> provideDataStore() {
    return new HashMap<>();
  }
}
