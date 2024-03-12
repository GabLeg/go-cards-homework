package com.example.gocardshomework.domain.game;

import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.infra.DeckRepository;
import com.example.gocardshomework.infra.GameRepository;
import com.example.gocardshomework.infra.event.EventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GameService {

  private final GameRepository gameRepository;
  private final GameFactory gameFactory;
  private final DeckRepository deckRepository;
  private final EventPublisher eventPublisher;

  public GameService(GameRepository gameRepository, GameFactory gameFactory, DeckRepository deckRepository, EventPublisher eventPublisher) {
    this.gameRepository = gameRepository;
    this.gameFactory = gameFactory;
    this.deckRepository = deckRepository;
    this.eventPublisher = eventPublisher;
  }

  public Game createGame() {
    Game newGame = gameFactory.createGame();
    gameRepository.saveNewGame(newGame);
    eventPublisher.publishGameEvent("New game created with id [%s].".formatted(newGame.getGameId()));
    return newGame;
  }

  public void deleteGame(String gameId) {
    gameRepository.deleteGame(gameId);
    eventPublisher.publishGameEvent("Game id [%s] was deleted.".formatted(gameId));
  }

  public void addDeckToGame(String gameId, String deckId) {
    Game game = gameRepository.getGameById(gameId);
    List<Card> newDeck = deckRepository.getDeckById(deckId);
    game.addDeck(newDeck);
    gameRepository.updateGame(game);
    eventPublisher.publishGameEvent("Deck id [%s] was added to Game id [%s].".formatted(deckId, gameId));
  }

  public void addPlayer(String gameId, String playerId) {
    Game game = gameRepository.getGameById(gameId);
    game.addPlayer(playerId);
    gameRepository.updateGame(game);
    eventPublisher.publishGameEvent("Player id [%s] was added to Game id [%s].".formatted(playerId, gameId));
  }

  public void removePlayer(String gameId, String playerId) {
    Game game = gameRepository.getGameById(gameId);
    game.removePlayer(playerId);
    gameRepository.updateGame(game);
    eventPublisher.publishGameEvent("Player id [%s] was removed to Game id [%s].".formatted(playerId, gameId));
  }

  public void dealCards(String gameId) {
    Game game = gameRepository.getGameById(gameId);
    game.dealCards();
    gameRepository.updateGame(game);
    eventPublisher.publishGameEvent("Player from Game id [%s] received new card.".formatted(gameId));
    sendEventForNewCardReceived(game);
  }

  public List<Card> retrievePlayerCards(String gameId, String playerId) {
    Game game = gameRepository.getGameById(gameId);
    return game.retrievePlayerCards(playerId);
  }

  private void sendEventForNewCardReceived(Game game) {
    Map<String, List<Card>> players = game.getPlayers();
    for (String playerId : players.keySet()) {
      List<Card> cards = players.get(playerId);
      Card lastCardReceived = cards.getLast();
      eventPublisher.publishPlayerEvent("Player id [%s] received new card [%s].".formatted(playerId, lastCardReceived.toString()));
    }
  }
}
