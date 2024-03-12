package com.example.gocardshomework.api.controllers;

import com.example.gocardshomework.api.dto.GameDto;
import com.example.gocardshomework.config.mapper.ModelMapper;
import com.example.gocardshomework.domain.game.GameService;
import com.example.gocardshomework.domain.game.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

  private GameService gameService;
  private ModelMapper mapper;

  public GameController(GameService gameService,
                        ModelMapper modelMapper) {
    this.gameService = gameService;
    this.mapper = modelMapper;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public GameDto createGame() {
    Game newGame =  gameService.createGame();
    return mapper.map(newGame, GameDto.class);
  }

  @GetMapping("/{gameId}")
  public String retrieveGameInformation(@PathVariable("gameId") String gameId) {
    return "";
  }

  @DeleteMapping("/{gameId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteGame(@PathVariable("gameId") String gameId) {
    gameService.deleteGame(gameId);
  }
}
