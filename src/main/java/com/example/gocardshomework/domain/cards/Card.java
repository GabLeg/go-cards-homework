package com.example.gocardshomework.domain.cards;

public class Card {

  private String name;
  private Suit suit;
  private Value value;

  public Card(Value value, Suit suit) {
    this.value = value;
    this.suit = suit;
  }
}
