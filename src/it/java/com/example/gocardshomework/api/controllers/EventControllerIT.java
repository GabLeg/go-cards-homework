package com.example.gocardshomework.api.controllers;

import com.example.gocardshomework.config.IntegrationTestParent;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EventControllerIT extends IntegrationTestParent {

  private static final String NEW_EVENT = "this is a new event";

  @Test
  void whenGetAllEvents_thenReturnHttp200() throws Exception {
    eventDatastore.add(NEW_EVENT);

    MvcResult mvcResult = this.mockMvc.perform(get("/api/v1/events"))
                                      .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                                      .andExpect(status().isOk())
                                      .andReturn();

    List<String> eventLists = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);
    assertThat(eventLists).containsExactly(NEW_EVENT);
  }

}