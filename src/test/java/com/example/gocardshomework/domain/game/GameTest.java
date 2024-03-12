package com.example.gocardshomework.domain.game;

import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.cards.Suit;
import com.example.gocardshomework.domain.cards.Value;
import com.example.gocardshomework.domain.exceptions.PlayerAlreadyInGameException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {

  private static final List<Card> DECK_OF_CARDS = List.of(new Card(Value.KING, Suit.CLUB));
  private static final String GAME_ID = "gameId123";
  private static final String PLAYER_ID = "playerId123";

  private Game game;

  @BeforeEach
  void setUp() {
    game = new Game(GAME_ID);
  }

  @Test
  void givenGameId_whenCreateGame_thenUseProvidedGameId() {
    assertThat(game.getGameId()).isEqualTo(GAME_ID);
  }

  @Test
  void givenNewDeck_whenAddDeck_thenCardsAreAdded() {
    game.addDeck(DECK_OF_CARDS);

    assertThat(game.getAvailableCards()).isEqualTo(DECK_OF_CARDS);
  }

  @Test
  void givenNewPlayer_whenAddPlayer_thenPlayerIsAdded() {
    game.addPlayer(PLAYER_ID);

    assertThat(game.getPlayers().containsKey(PLAYER_ID)).isTrue();
  }

  @Test
  void givenExistingPlayer_whenAddPlayer_thenPlayerIsAdded() {
    game.addPlayer(PLAYER_ID);

    Executable addPlayer = () -> game.addPlayer(PLAYER_ID);

    assertThrows(PlayerAlreadyInGameException.class, addPlayer);
  }

  @Test
  void givenExistingPlayer_whenRemovePlayer_thenPlayerIsRemoved() {
    game.addPlayer(PLAYER_ID);

    game.removePlayer(PLAYER_ID);

    assertThat(game.getPlayers().containsKey(PLAYER_ID)).isFalse();
  }
}