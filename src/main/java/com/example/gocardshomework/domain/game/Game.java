package com.example.gocardshomework.domain.game;

import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.exceptions.NotEnoughCardsToDealException;
import com.example.gocardshomework.domain.exceptions.PlayerAlreadyInGameException;

import java.util.*;

public class Game {

  private final String gameId;
  private final List<Card> availableCards;
  private final List<Card> unavailableCards;
  private final Map<String, List<Card>> players;

  public Game(String gameId) {
    this.gameId = gameId;
    this.availableCards = new ArrayList<>();
    this.unavailableCards = new ArrayList<>();
    this.players = new HashMap<>();
  }

  public String getGameId() {
    return gameId;
  }

  public List<Card> getAvailableCards() {
    return availableCards;
  }

  public List<Card> getUnavailableCards() {
    return unavailableCards;
  }

  public Map<String, List<Card>> getPlayers() {
    return players;
  }

  public List<Card> retrievePlayerCards(String playerId) {
    return players.get(playerId);
  }

  public void addDeck(List<Card> newDeck) {
    availableCards.addAll(newDeck);
    Collections.shuffle(availableCards);
  }

  public void addPlayer(String playerId) {
    if (players.containsKey(playerId)) {
      throw new PlayerAlreadyInGameException();
    }
    players.put(playerId, new ArrayList<>());
  }

  public void removePlayer(String playerId) {
    players.remove(playerId);
  }

  public void dealCards() {
    Set<String> playerIds = players.keySet();
    if (availableCards.size() < playerIds.size()) {
      throw new NotEnoughCardsToDealException();
    }
    for (String playerId : playerIds) {
      Card cardToDeal = availableCards.getFirst();
      players.get(playerId).add(cardToDeal);
      unavailableCards.add(cardToDeal);
      availableCards.removeFirst();
    }
  }
}
