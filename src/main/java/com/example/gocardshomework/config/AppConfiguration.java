package com.example.gocardshomework.config;

import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.game.Game;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class AppConfiguration {

  @Bean
  public Map<String, Game> provideGameDatastore() {
    return new HashMap<>();
  }

  @Bean
  public Map<String, List<Card>> provideDeckDatastore() {
    return new HashMap<>();
  }

  @Bean
  public List<String> provideEventDatastore() {
    return new ArrayList<>();
  }
}
