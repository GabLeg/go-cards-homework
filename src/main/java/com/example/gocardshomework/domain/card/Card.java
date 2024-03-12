package com.example.gocardshomework.domain.card;

public class Card {

  private String name;
  private Suit suit;
  private int value;

  public Card(String name, Suit suit, int value) {
    this.name = name;
    this.suit = suit;
    this.value = value;
  }
}
