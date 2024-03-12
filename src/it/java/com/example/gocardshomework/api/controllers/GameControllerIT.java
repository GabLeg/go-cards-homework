package com.example.gocardshomework.api.controllers;

import com.example.gocardshomework.api.dto.GameDto;
import com.example.gocardshomework.config.IntegrationTestParent;
import com.example.gocardshomework.domain.game.Game;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GameControllerIT extends IntegrationTestParent {

  @Test
  void whenCreateGame_thenReturnHttp201() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(post("/api/v1/games"))
                                      .andExpect(status().isCreated())
                                      .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                                      .andReturn();

    GameDto gameDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), GameDto.class);
    assertThat(gameDto.getGameId()).isNotEmpty();
  }

  @Test
  void givenGameAlreadyInDatastore_whenCreateGame_thenReturnHttp409() throws Exception {
    datastore.put(GAME_ID, new Game(GAME_ID));

    this.mockMvc.perform(post("/api/v1/games")).andExpect(status().isConflict());
  }
}
