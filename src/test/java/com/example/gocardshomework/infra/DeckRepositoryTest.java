package com.example.gocardshomework.infra;

import com.example.gocardshomework.domain.IdGenerator;
import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.cards.Suit;
import com.example.gocardshomework.domain.cards.Value;
import com.example.gocardshomework.infra.exceptions.DeckNotAvailableException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class DeckRepositoryTest {

  private static final List<Card> DECK_OF_CARDS = List.of(new Card(Value.ACE, Suit.CLUB));
  private static final String DECK_ID = "deck123";
  private static final String UNKNOWN_DECK_ID = "unknownDeckId";

  @Mock
  private IdGenerator idGenerator;
  private Map<String, List<Card>> datastore;
  private DeckRepository deckRepository;

  @BeforeEach
  void setUp() {
    datastore = new HashMap<>();
    deckRepository = new DeckRepository(datastore, idGenerator);
  }

  @Test
  void givenDeck_whenSaveDeck_thenDeckIsSaved() {
    given(idGenerator.generate()).willReturn(DECK_ID);

    String generatedId = deckRepository.saveDeck(DECK_OF_CARDS);

    assertThat(generatedId).isEqualTo(DECK_ID);
    assertThat(datastore.get(generatedId)).isEqualTo(DECK_OF_CARDS);
  }

  @Test
  void givenDeckId_whenGetDeckById_thenReturnDeck() {
    datastore.put(DECK_ID, DECK_OF_CARDS);

    List<Card> deck = deckRepository.getDeckById(DECK_ID);

    assertThat(deck).isEqualTo(DECK_OF_CARDS);
  }

  @Test
  void givenDeckId_whenGetDeckByUd_thenDeckIsRemoved() {
    datastore.put(DECK_ID, DECK_OF_CARDS);

     deckRepository.getDeckById(DECK_ID);

    assertThat(datastore.get(DECK_ID)).isNull();
  }

  @Test
  void givenUnknownDeckId_whenGetDeckById_thenThrowDeckNotAvailableException() {
    Executable getDeckById = () -> deckRepository.getDeckById(UNKNOWN_DECK_ID);

    assertThrows(DeckNotAvailableException.class, getDeckById);
  }
}