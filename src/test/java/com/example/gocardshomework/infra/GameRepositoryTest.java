package com.example.gocardshomework.infra;

import com.example.gocardshomework.domain.game.Game;
import com.example.gocardshomework.infra.exceptions.GameAlreadyExistsException;
import com.example.gocardshomework.infra.exceptions.GameNotAvailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class GameRepositoryTest {

  private static final String GAME_ID = "gameId345";
  private static final String UNKNOWN_GAME_ID = "unknownGameId";

  @Mock
  private Game game;
  private Map<String, Game> datastore;
  private GameRepository gameRepository;

  @BeforeEach
  void setUp() {
    datastore = new HashMap<>();
    gameRepository = new GameRepository(datastore);
  }

  @Test
  void givenGame_whenSaveNewGame_thenGameIsInDatastore() {
    given(game.getGameId()).willReturn(GAME_ID);

    gameRepository.saveNewGame(game);

    assertThat(datastore.get(GAME_ID)).isEqualTo(game);
  }

  @Test
  void givenGameAlreadyInDatastore_whenSaveNewGame_thenThrowGameAlreadyExistsException() {
    datastore.put(GAME_ID, game);
    given(game.getGameId()).willReturn(GAME_ID);

    Executable saveNewGame = () -> gameRepository.saveNewGame(game);

    assertThrows(GameAlreadyExistsException.class, saveNewGame);
  }

  @Test
  void givenGameId_whenDeleteGame_thenGameIsRemovedFromDatastore() {
    datastore.put(GAME_ID, game);

    gameRepository.deleteGame(GAME_ID);

    assertThat(datastore.get(GAME_ID)).isNull();
  }

  @Test
  void givenGameId_whenGetGameById_thenReturnGame() {
    datastore.put(GAME_ID, game);

    Game storedGame = gameRepository.getGameById(GAME_ID);

    assertThat(storedGame).isEqualTo(game);
  }

  @Test
  void givenUnknownDeckId_whenGetDeckById_thenThrowDeckNotAvailableException() {
    Executable getGameById = () -> gameRepository.getGameById(UNKNOWN_GAME_ID);

    assertThrows(GameNotAvailableException.class, getGameById);
  }
}