package com.example.gocardshomework.domain;

import com.example.gocardshomework.domain.game.Game;
import com.example.gocardshomework.domain.game.GameFactory;
import com.example.gocardshomework.infra.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameService {

  private final GameRepository gameRepository;
  private final GameFactory gameFactory;

  @Autowired
  public GameService(GameRepository gameRepository, GameFactory gameFactory) {
    this.gameRepository = gameRepository;
    this.gameFactory = gameFactory;
  }

  public Game createGame() {
    Game newGame = gameFactory.createGame();
    gameRepository.saveNewGame(newGame);
    return newGame;
  }

  public void deleteGame(String gameId) {
    gameRepository.deleteGame(gameId);
  }
}
