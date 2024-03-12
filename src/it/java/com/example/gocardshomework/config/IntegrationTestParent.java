package com.example.gocardshomework.config;

import com.example.gocardshomework.GoCardsHomeworkApplication;
import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.game.Game;
import com.example.gocardshomework.domain.IdGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.reset;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = {GoCardsHomeworkApplication.class})
@Import(TestConfig.class)
@TestPropertySource("classpath:application-test.yml")
@ActiveProfiles("test")
public abstract class IntegrationTestParent {

  protected static final String GAME_ID = UUID.randomUUID().toString();
  protected static final String DECK_ID = UUID.randomUUID().toString();
  protected static final String PLAYER_ID = UUID.randomUUID().toString();

  @Autowired
  private WebApplicationContext context;
  @Autowired
  protected ObjectMapper objectMapper;
  @Autowired
  protected IdGenerator idGenerator;
  @Autowired
  protected Map<String, Game> gameDatastore;
  @Autowired
  protected Map<String, List<Card>> deckDatastore;
  @Autowired
  protected List<String> eventDatastore;

  protected MockMvc mockMvc;

  @BeforeEach
  void integrationSetUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    gameDatastore.clear();
    deckDatastore.clear();
    eventDatastore.clear();
    reset(idGenerator);
  }
}
