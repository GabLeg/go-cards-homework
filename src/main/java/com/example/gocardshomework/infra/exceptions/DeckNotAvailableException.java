package com.example.gocardshomework.infra.exceptions;

public class DeckNotAvailableException extends RuntimeException {

  private static final String MESSAGE = "Deck with id [%s] isn't available.";

  public DeckNotAvailableException(String deckId) {
    super(MESSAGE.formatted(deckId));
  }
}
