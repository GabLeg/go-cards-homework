package com.example.gocardshomework.infra;

import com.example.gocardshomework.domain.game.Game;
import com.example.gocardshomework.infra.exceptions.GameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GameRepository {

  private Map<String, Game> datastore;

  @Autowired
  public GameRepository(Map<String, Game> datastore) {
    this.datastore = datastore;
  }

  public synchronized void saveNewGame(Game game) {
    String gameId = game.getGameId();
    if (datastore.containsKey(gameId)) {
      throw new GameAlreadyExistsException(gameId);
    }
    datastore.put(gameId, game);
  }
}
