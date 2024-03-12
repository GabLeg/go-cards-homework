package com.example.gocardshomework.domain.game;

import org.springframework.stereotype.Component;

@Component
public class GameFactory {

  private final GameIdGenerator gameIdGenerator;

  public GameFactory(GameIdGenerator gameIdGenerator) {
    this.gameIdGenerator = gameIdGenerator;
  }

  public Game createGame() {
    String gameId = gameIdGenerator.generate();
    return new Game(gameId);
  }
}
