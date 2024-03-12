package com.example.gocardshomework.domain;

import com.example.gocardshomework.domain.game.Game;
import com.example.gocardshomework.domain.game.GameFactory;
import com.example.gocardshomework.domain.game.GameService;
import com.example.gocardshomework.infra.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

  private static final String GAME_ID = "gameId321";

  @Mock
  private GameRepository gameRepository;
  @Mock
  private GameFactory gameFactory;
  @Mock
  private Game game;

  private GameService gameService;

  @BeforeEach
  void setUp() {
    gameService = new GameService(gameRepository, gameFactory);
  }

  @Test
  void whenCreateGame_thenGameCreated() {
    given(gameFactory.createGame()).willReturn(game);

    Game newGame = gameService.createGame();

    assertThat(newGame).isEqualTo(game);
  }

  @Test
  void whenCreateGame_thenGameIsSaved() {
    Game game = gameService.createGame();

    verify(gameRepository).saveNewGame(game);
  }

  @Test
  void givenGameId_whenDeleteGame_thenCallRepository() {
    gameService.deleteGame(GAME_ID);

    verify(gameRepository).deleteGame(GAME_ID);
  }
}