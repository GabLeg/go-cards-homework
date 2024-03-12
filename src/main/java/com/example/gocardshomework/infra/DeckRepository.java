package com.example.gocardshomework.infra;

import com.example.gocardshomework.domain.IdGenerator;
import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.infra.exceptions.DeckNotAvailableException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public class DeckRepository {

  private final Map<String, List<Card>> datastore;
  private final IdGenerator idGenerator;

  public DeckRepository(Map<String, List<Card>> datastore, IdGenerator idGenerator) {
    this.datastore = datastore;
    this.idGenerator = idGenerator;
  }

  public String saveDeck(List<Card> deck) {
    String deckId = idGenerator.generate();
    datastore.put(deckId, deck);
    return deckId;
  }

  public synchronized List<Card> getDeckById(String deckId) {
    List<Card> deck = datastore.get(deckId);
    if (deck == null) {
      throw new DeckNotAvailableException(deckId);
    }
    datastore.remove(deckId);
    return deck;
  }
}
