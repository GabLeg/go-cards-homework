package com.example.gocardshomework.domain.game;

import com.example.gocardshomework.domain.IdGenerator;
import org.springframework.stereotype.Component;

@Component
public class GameFactory {

  private final IdGenerator idGenerator;

  public GameFactory(IdGenerator idGenerator) {
    this.idGenerator = idGenerator;
  }

  public Game createGame() {
    String gameId = idGenerator.generate();
    return new Game(gameId);
  }
}
