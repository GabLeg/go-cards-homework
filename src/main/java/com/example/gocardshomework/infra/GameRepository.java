package com.example.gocardshomework.infra;

import com.example.gocardshomework.domain.game.Game;
import com.example.gocardshomework.infra.exceptions.GameAlreadyExistsException;
import com.example.gocardshomework.infra.exceptions.GameNotAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class GameRepository {

  private final Map<String, Game> datastore;

  public GameRepository(Map<String, Game> datastore) {
    this.datastore = datastore;
  }

  public void saveNewGame(Game game) {
    String gameId = game.getGameId();
    if (datastore.containsKey(gameId)) {
      throw new GameAlreadyExistsException(gameId);
    }
    datastore.put(gameId, game);
  }

  public void updateGame(Game game) {
    datastore.replace(game.getGameId(), game);
  }

  public void deleteGame(String gameId) {
    datastore.remove(gameId);
  }

  public Game getGameById(String gameId) {
    Game game = datastore.get(gameId);
    if (game == null) {
      throw new GameNotAvailableException(gameId);
    }
    return game;
  }
}
