package com.example.gocardshomework.domain.cards;

public class Card {

  private Suit suit;
  private Value value;

  public Card(Value value, Suit suit) {
    this.value = value;
    this.suit = suit;
  }

  public int getIntValue() {
    return value.getValue();
  }

  @Override
  public String toString() {
    return "%s of %s".formatted(value.getName(), suit.toString().toLowerCase());
  }
}
