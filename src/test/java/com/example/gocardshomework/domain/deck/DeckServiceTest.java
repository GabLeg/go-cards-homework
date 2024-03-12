package com.example.gocardshomework.domain.deck;

import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.cards.Suit;
import com.example.gocardshomework.domain.cards.Value;
import com.example.gocardshomework.infra.DeckRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeckServiceTest {

  private static final List<Card> DECK_OF_CARDS = List.of(new Card(Value.ACE, Suit.DIAMONDS));
  private static final String DECK_ID = "deck456";

  @Mock
  private DeckFactory deckFactory;
  @Mock
  private DeckRepository deckRepository;
  private DeckService deckService;

  @BeforeEach
  void setUp() {
    deckService = new DeckService(deckFactory, deckRepository);
  }

  @Test
  void whenCreateDeck_thenDeckIsCreatedAndSaved() {
    given(deckFactory.createDeck()).willReturn(DECK_OF_CARDS);
    given(deckRepository.saveDeck(DECK_OF_CARDS)).willReturn(DECK_ID);

    String deckId = deckService.createDeck();

    assertThat(deckId).isEqualTo(DECK_ID);
  }
}