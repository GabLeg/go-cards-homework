package com.example.gocardshomework.domain.deck;

import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.cards.Suit;
import com.example.gocardshomework.domain.cards.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DeckFactory {

  private final List<Card> fiftyTwoDeckCard;

  public DeckFactory() {
    fiftyTwoDeckCard = new ArrayList<>();
    for (Value value : Value.values()) {
      for (Suit suit : Suit.values()) {
        fiftyTwoDeckCard.add(new Card(value, suit));
      }
    }
  }

  public List<Card> createDeck() {
    return new ArrayList<>(fiftyTwoDeckCard);
  }
}
