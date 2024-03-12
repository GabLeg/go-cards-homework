package com.example.gocardshomework.api.controllers;

import com.example.gocardshomework.api.dto.GameDto;
import com.example.gocardshomework.config.IntegrationTestParent;
import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.cards.Suit;
import com.example.gocardshomework.domain.cards.Value;
import com.example.gocardshomework.domain.game.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameControllerIT extends IntegrationTestParent {

  private static final List<Card> EXISTING_DECK = List.of(new Card(Value.EIGHT, Suit.CLUB));
  private static final String UNKNOWN_DECK_ID = "unknownDeckId123";
  private static final String UNKNOWN_GAME_ID = "unknownGameId321";

  @BeforeEach
  void setUp() {
    given(idGenerator.generate()).willReturn(GAME_ID);
  }

  @Test
  void whenCreateGame_thenReturnHttp201() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(post("/api/v1/games"))
                                      .andExpect(status().isCreated())
                                      .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                                      .andReturn();

    GameDto gameDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), GameDto.class);
    assertThat(gameDto.getGameId()).isEqualTo(GAME_ID);
  }

  @Test
  void givenGameAlreadyInDatastore_whenCreateGame_thenReturnHttp409() throws Exception {
    gameDatastore.put(GAME_ID, new Game(GAME_ID));

    this.mockMvc.perform(post("/api/v1/games")).andExpect(status().isConflict());
  }

  @Test
  void givenGameId_whenDeleteGame_thenReturnHttp204() throws Exception {
    gameDatastore.put(GAME_ID, new Game(GAME_ID));

    this.mockMvc.perform(delete("/api/v1/games/%s".formatted(GAME_ID))).andExpect(status().isNoContent());

    assertThat(gameDatastore.get(GAME_ID)).isNull();
  }

  @Test
  void givenGameId_whenDealCards_thenReturnHttp204() throws Exception {
    Game game = new Game(GAME_ID);
    game.addPlayer(PLAYER_ID);
    game.addDeck(EXISTING_DECK);
    gameDatastore.put(GAME_ID, game);

    this.mockMvc.perform(get("/api/v1/games/%s/deal-cards".formatted(GAME_ID))).andExpect(status().isNoContent());
  }

  @Test
  void givenGameIdWithNotEnoughCards_whenDealCards_thenReturnHttp400() throws Exception {
    Game game = new Game(GAME_ID);
    game.addPlayer(PLAYER_ID);
    gameDatastore.put(GAME_ID, game);

    this.mockMvc.perform(get("/api/v1/games/%s/deal-cards".formatted(GAME_ID))).andExpect(status().isBadRequest());
  }

  @Test
  void givenGameIdAndDeckId_whenAddDeckToGame_thenReturnHttp204() throws Exception {
    gameDatastore.put(GAME_ID, new Game(GAME_ID));
    deckDatastore.put(DECK_ID, EXISTING_DECK);

    this.mockMvc.perform(put("/api/v1/games/%s/decks/%s".formatted(GAME_ID, DECK_ID))).andExpect(status().isNoContent());

    Game game = gameDatastore.get(GAME_ID);
    assertThat(game.getAvailableCards()).isEqualTo(EXISTING_DECK);
  }

  @Test
  void givenUnknownGameId_whenAddDeckToGame_thenReturnHttp404() throws Exception {
    this.mockMvc.perform(put("/api/v1/games/%s/decks/%s".formatted(UNKNOWN_GAME_ID, DECK_ID))).andExpect(status().isNotFound());
  }

  @Test
  void givenUnknownDeckId_whenAddDeckToGame_thenReturnHttp404() throws Exception {
    gameDatastore.put(GAME_ID, new Game(GAME_ID));

    this.mockMvc.perform(put("/api/v1/games/%s/decks/%s".formatted(GAME_ID, UNKNOWN_DECK_ID))).andExpect(status().isNotFound());
  }

  @Test
  void givenGameIdAndPlayerId_whenAddPlayer_thenReturnHttp204() throws Exception {
    gameDatastore.put(GAME_ID, new Game(GAME_ID));

    this.mockMvc.perform(put("/api/v1/games/%s/players/%s".formatted(GAME_ID, PLAYER_ID))).andExpect(status().isNoContent());
  }

  @Test
  void givenPlayerAlreadyInGame_whenAddPlayer_thenReturnHttp409() throws Exception {
    Game game = new Game(GAME_ID);
    game.addPlayer(PLAYER_ID);
    gameDatastore.put(GAME_ID, game);

    this.mockMvc.perform(put("/api/v1/games/%s/players/%s".formatted(GAME_ID, PLAYER_ID))).andExpect(status().isConflict());
  }

  @Test
  void givenGameIdAndPlayerId_whenRemovePlayer_thenReturnHttp204() throws Exception {
    Game game = new Game(GAME_ID);
    game.addPlayer(PLAYER_ID);
    gameDatastore.put(GAME_ID, game);

    this.mockMvc.perform(delete("/api/v1/games/%s/players/%s".formatted(GAME_ID, PLAYER_ID))).andExpect(status().isNoContent());
  }
}
