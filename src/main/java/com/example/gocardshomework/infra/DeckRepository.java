package com.example.gocardshomework.infra;

import com.example.gocardshomework.domain.IdGenerator;
import com.example.gocardshomework.domain.cards.Card;
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
}
