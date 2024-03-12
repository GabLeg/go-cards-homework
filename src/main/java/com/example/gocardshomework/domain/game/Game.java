package com.example.gocardshomework.domain.game;

import com.example.gocardshomework.domain.cards.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game {

  private String gameId;
  private List<Card> availableCards;
  private List<Card> usedCards;

  public Game(String gameId) {
    this.gameId = gameId;
    this.availableCards = new ArrayList<>();
    this.usedCards = new ArrayList<>();
  }

  public String getGameId() {
    return gameId;
  }

  public List<Card> getAvailableCards() {
    return availableCards;
  }

  public void addDeck(List<Card> newDeck) {
    availableCards.addAll(newDeck);
    Collections.shuffle(availableCards);
  }
}
