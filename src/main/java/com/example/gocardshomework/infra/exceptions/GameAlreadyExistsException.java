package com.example.gocardshomework.infra.exceptions;

public class GameAlreadyExistsException extends RuntimeException {

  private static final String MESSAGE = "Game with id [%s] already exists.";

  public GameAlreadyExistsException(String gameId) {
    super(MESSAGE.formatted(gameId));
  }
}
