package com.example.gocardshomework.domain.game;

import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class GameIdGeneratorTest {

  @Test
  void whenGenerate_thenReturnNewId() {
    GameIdGenerator gameIdGenerator = new GameIdGenerator();

    String newId = gameIdGenerator.generate();

    assertThat(newId).isNotEmpty();
  }

}