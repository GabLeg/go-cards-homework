package com.example.gocardshomework.domain;

import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.cards.Suit;
import com.example.gocardshomework.domain.cards.Value;
import com.example.gocardshomework.domain.game.Game;
import com.example.gocardshomework.domain.game.GameFactory;
import com.example.gocardshomework.domain.game.GameService;
import com.example.gocardshomework.infra.DeckRepository;
import com.example.gocardshomework.infra.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class GameServiceTest {

  private static final List<Card> DECK_OF_CARDS = List.of(new Card(Value.QUEEN, Suit.CLUB));
  private static final String GAME_ID = "gameId321";
  private static final String DECK_ID = "deckId321";
  private static final String PLAYER_ID = "player567";

  @Mock
  private GameRepository gameRepository;
  @Mock
  private GameFactory gameFactory;
  @Mock
  private DeckRepository deckRepository;
  @Mock
  private Game game;

  private GameService gameService;

  @BeforeEach
  void setUp() {
    gameService = new GameService(gameRepository, gameFactory, deckRepository);
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

  @Test
  void givenGameIdAndDeckId_whenAddDeckToGame_thenGameIsUpdated() {
    given(gameRepository.getGameById(GAME_ID)).willReturn(game);
    given(deckRepository.getDeckById(DECK_ID)).willReturn(DECK_OF_CARDS);

    gameService.addDeckToGame(GAME_ID, DECK_ID);

    verify(game).addDeck(DECK_OF_CARDS);
    verify(gameRepository).updateGame(game);
  }

  @Test
  void givenGameIdAndPlayerId_whenAddPlayer_thenPlayerIsAddedToGame() {
    given(gameRepository.getGameById(GAME_ID)).willReturn(game);

    gameService.addPlayer(GAME_ID, PLAYER_ID);

    verify(game).addPlayer(PLAYER_ID);
    verify(gameRepository).updateGame(game);
  }

  @Test
  void givenGameIdAndPlayerId_whenRemovePlayer_thenPlayerIsRemovedFromGame() {
    given(gameRepository.getGameById(GAME_ID)).willReturn(game);

    gameService.removePlayer(GAME_ID, PLAYER_ID);

    verify(game).removePlayer(PLAYER_ID);
    verify(gameRepository).updateGame(game);
  }
}