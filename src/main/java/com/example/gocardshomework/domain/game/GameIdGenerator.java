package com.example.gocardshomework.domain.game;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GameIdGenerator {

  public String generate() {
    return UUID.randomUUID().toString();
  }
}
