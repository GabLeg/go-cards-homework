package com.example.gocardshomework.api.controllers;

import com.example.gocardshomework.domain.exceptions.NotEnoughCardsToDealException;
import com.example.gocardshomework.domain.exceptions.PlayerAlreadyInGameException;
import com.example.gocardshomework.infra.exceptions.DeckNotAvailableException;
import com.example.gocardshomework.infra.exceptions.GameAlreadyExistsException;
import com.example.gocardshomework.infra.exceptions.GameNotAvailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionMapper extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = GameAlreadyExistsException.class)
  public ResponseEntity<String> gameAlreadyExists(RuntimeException exception, WebRequest request) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(value = DeckNotAvailableException.class)
  public ResponseEntity<String> deckNotAvailable(RuntimeException exception, WebRequest request) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = GameNotAvailableException.class)
  public ResponseEntity<String> gameNotAvailable(RuntimeException exception, WebRequest request) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = PlayerAlreadyInGameException.class)
  public ResponseEntity<String> playerAlreadyInGame(RuntimeException exception, WebRequest request) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.CONFLICT);
  }

  @ExceptionHandler(value = NotEnoughCardsToDealException.class)
  public ResponseEntity<String> notEnoughCardsToDeal(RuntimeException exception, WebRequest request) {
    return new ResponseEntity<>(exception.getMessage(), HttpStatus.BAD_REQUEST);
  }
}
