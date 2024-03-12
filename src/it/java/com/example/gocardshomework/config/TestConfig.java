package com.example.gocardshomework.config;

import com.example.gocardshomework.domain.game.GameIdGenerator;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class TestConfig {

  @Bean
  @Primary
  public GameIdGenerator provideGameFactory() {
    return mock(GameIdGenerator.class);
  }
}
