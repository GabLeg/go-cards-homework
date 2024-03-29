package com.example.gocardshomework.domain.game;

import com.example.gocardshomework.domain.IdGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GameFactoryTest {

  private static final String GAME_ID = "gameId1234";

  @Mock
  private IdGenerator idGenerator;
  private GameFactory gameFactory;

  @BeforeEach
  void setUp() {
    gameFactory = new GameFactory(idGenerator);
  }

  @Test
  void whenCreateGame_thenNewGameIsCreated() {
    given(idGenerator.generate()).willReturn(GAME_ID);

    Game newGame = gameFactory.createGame();

    assertThat(newGame).isNotNull();
    assertThat(newGame.getGameId()).isEqualTo(GAME_ID);
  }

}