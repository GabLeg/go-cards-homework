package com.example.gocardshomework.infra.exceptions;

public class GameNotAvailableException extends RuntimeException {

  private static final String MESSAGE = "Game with id [%s] isn't available.";

  public GameNotAvailableException(String deckId) {
    super(MESSAGE.formatted(deckId));
  }
}
