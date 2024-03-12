package com.example.gocardshomework.domain.game;

import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.infra.DeckRepository;
import com.example.gocardshomework.infra.GameRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameService {

  private final GameRepository gameRepository;
  private final GameFactory gameFactory;
  private final DeckRepository deckRepository;

  public GameService(GameRepository gameRepository, GameFactory gameFactory, DeckRepository deckRepository) {
    this.gameRepository = gameRepository;
    this.gameFactory = gameFactory;
    this.deckRepository = deckRepository;
  }

  public Game createGame() {
    Game newGame = gameFactory.createGame();
    gameRepository.saveNewGame(newGame);
    return newGame;
  }

  public void deleteGame(String gameId) {
    gameRepository.deleteGame(gameId);
  }

  public void addDeckToGame(String gameId, String deckId) {
    Game game = gameRepository.getGameById(gameId);
    List<Card> newDeck = deckRepository.getDeckById(deckId);
    game.addDeck(newDeck);
    gameRepository.updateGame(game);
  }

  public void addPlayer(String gameId, String playerId) {
    Game game = gameRepository.getGameById(gameId);
    game.addPlayer(playerId);
    gameRepository.updateGame(game);
  }
}
