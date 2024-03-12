package com.example.gocardshomework.api.controllers;

import com.example.gocardshomework.domain.deck.DeckService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/decks")
public class DeckController {

  private final DeckService deckService;

  public DeckController(DeckService deckService) {
    this.deckService = deckService;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public String createDeck() {
    return deckService.createDeck();
  }
}
