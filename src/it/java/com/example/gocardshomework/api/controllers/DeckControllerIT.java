package com.example.gocardshomework.api.controllers;

import com.example.gocardshomework.config.IntegrationTestParent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MvcResult;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeckControllerIT extends IntegrationTestParent {

  @BeforeEach
  void setUp() {
    given(idGenerator.generate()).willReturn(DECK_ID);
  }

  @Test
  void whenCreateGame_thenReturnHttp201() throws Exception {
    MvcResult mvcResult = this.mockMvc.perform(post("/api/v1/decks")).andExpect(status().isCreated()).andReturn();

    assertThat(mvcResult.getResponse().getContentAsString()).isEqualTo(DECK_ID);
  }

}