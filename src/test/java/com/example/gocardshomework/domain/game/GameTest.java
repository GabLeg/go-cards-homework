package com.example.gocardshomework.domain.game;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class GameTest {

  private static final String GAME_ID = "gameId123";

  @Test
  void givenGameId_whenCreateGame_thenUseProvidedGameId() {
    Game game = new Game(GAME_ID);

    assertThat(game.getGameId()).isEqualTo(GAME_ID);
  }

}