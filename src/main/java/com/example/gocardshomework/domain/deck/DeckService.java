package com.example.gocardshomework.domain.deck;

import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.infra.DeckRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckService {

  private final DeckFactory deckFactory;
  private final DeckRepository deckRepository;

  public DeckService(DeckFactory deckFactory, DeckRepository deckRepository) {
    this.deckFactory = deckFactory;
    this.deckRepository = deckRepository;
  }

  public String createDeck() {
    List<Card> deck = deckFactory.createDeck();
    return deckRepository.saveDeck(deck);
  }
}
