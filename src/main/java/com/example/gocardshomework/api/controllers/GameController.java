package com.example.gocardshomework.api.controllers;

import com.example.gocardshomework.api.dto.GameDto;
import com.example.gocardshomework.config.mapper.ModelMapper;
import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.game.Game;
import com.example.gocardshomework.domain.game.GameService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

  private final GameService gameService;
  private final ModelMapper mapper;

  public GameController(GameService gameService, ModelMapper modelMapper) {
    this.gameService = gameService;
    this.mapper = modelMapper;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GameDto createGame() {
    Game newGame = gameService.createGame();
    return mapper.map(newGame, GameDto.class);
  }

  @DeleteMapping("/{gameId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteGame(@PathVariable("gameId") String gameId) {
    gameService.deleteGame(gameId);
  }

  @GetMapping("/{gameId}/deal-cards")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void dealCards(@PathVariable("gameId") String gameId) {
    gameService.dealCards(gameId);
  }

  @PutMapping("/{gameId}/decks/{deckId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void addDeckToGame(@PathVariable("gameId") String gameId, @PathVariable("deckId") String deckId) {
    gameService.addDeckToGame(gameId, deckId);
  }

  @PutMapping("/{gameId}/players/{playerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void addPlayer(@PathVariable("gameId") String gameId, @PathVariable("playerId") String playerId) {
    gameService.addPlayer(gameId, playerId);
  }

  @DeleteMapping("/{gameId}/players/{playerId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void removePlayer(@PathVariable("gameId") String gameId, @PathVariable("playerId") String playerId) {
    gameService.removePlayer(gameId, playerId);
  }

  @GetMapping("/{gameId}/players/{playerId}/cards")
  public List<String> getPlayerCards(@PathVariable("gameId") String gameId, @PathVariable("playerId") String playerId) {
    return gameService.retrievePlayerCards(gameId, playerId).stream().map(Card::toString).collect(Collectors.toList());
  }
}
