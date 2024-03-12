package com.example.gocardshomework.domain.cards;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CardTest {

  @Test
  void whenToString_thenStringIsWellFormatted() {
    Card card = new Card(Value.ACE, Suit.SPADES);

    String stringCardValue = card.toString();

    assertThat(stringCardValue).isEqualTo("Ace of spades");
  }

}