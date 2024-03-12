package com.example.gocardshomework.domain;

import com.example.gocardshomework.domain.IdGenerator;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

class IdGeneratorTest {

  @Test
  void whenGenerate_thenReturnNewId() {
    IdGenerator idGenerator = new IdGenerator();

    String newId = idGenerator.generate();

    assertThat(newId).isNotEmpty();
  }

}