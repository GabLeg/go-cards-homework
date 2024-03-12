package com.example.gocardshomework.api.controllers;

import com.example.gocardshomework.api.dto.PlayerStatisticsDto;
import com.example.gocardshomework.config.IntegrationTestParent;
import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.cards.Suit;
import com.example.gocardshomework.domain.cards.Value;
import com.example.gocardshomework.domain.game.Game;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.UUID;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class GameStatisticsControllerTest extends IntegrationTestParent {

  protected static final String PLAYER_ID2 = UUID.randomUUID().toString();

  private static final List<Card> EXISTING_DECK = List.of(new Card(Value.KING, Suit.SPADES), new Card(Value.QUEEN, Suit.CLUBS));

  @Test
  void givenGameId_whenGetPlayersStatistics_thenReturnHttp200() throws Exception {
    Game game = new Game(GAME_ID);
    game.addPlayer(PLAYER_ID);
    game.addPlayer(PLAYER_ID2);
    game.addDeck(EXISTING_DECK);
    game.dealCards();
    gameDatastore.put(GAME_ID, game);

    MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/game-statistics/%s/players".formatted(GAME_ID)))
                                      .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                                      .andExpect(status().isOk())
                                      .andReturn();

    List<PlayerStatisticsDto> statistics = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), new TypeReference<>() {
    });
    assertThat(statistics.get(0).getCardsValue()).isEqualTo(Value.KING.getValue());
  }

  @Test
  void givenGameId_whenGetUndeltStatistic_thenReturnHttp200() throws Exception {
    Game game = new Game(GAME_ID);
    game.addDeck(EXISTING_DECK);
    gameDatastore.put(GAME_ID, game);

    this.mockMvc.perform(get("/api/v1/game-statistics/%s/undealt".formatted(GAME_ID))).andExpect(status().isOk());
  }
}