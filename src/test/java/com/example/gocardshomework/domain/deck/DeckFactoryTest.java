package com.example.gocardshomework.domain.deck;

import com.example.gocardshomework.domain.cards.Card;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;

class DeckFactoryTest {

  private DeckFactory deckFactory;

  @BeforeEach
  void setUp() {
    deckFactory = new DeckFactory();
  }

  @Test
  void whenCreateDeck_thenDeckContains52Cards() {
    List<Card> deck = deckFactory.createDeck();

    assertThat(deck).hasSize(52);
  }
}