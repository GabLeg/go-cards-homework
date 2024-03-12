package com.example.gocardshomework.domain.deck;

import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.infra.DeckRepository;
import com.example.gocardshomework.infra.event.EventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

  private final DeckFactory deckFactory;
  private final DeckRepository deckRepository;
  private final EventPublisher eventPublisher;

  public DeckService(DeckFactory deckFactory, DeckRepository deckRepository, EventPublisher eventPublisher) {
    this.deckFactory = deckFactory;
    this.deckRepository = deckRepository;
    this.eventPublisher = eventPublisher;
  }

  public String createDeck() {
    List<Card> deck = deckFactory.createDeck();
    String deckId = deckRepository.saveDeck(deck);
    eventPublisher.publishDeckEvent("Deck id [%s] was created.".formatted(deckId));
    return deckId;
  }
}
