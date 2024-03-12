package com.example.gocardshomework.infra.event;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class EventRepositoryTest {

  private static final String MESSAGE = "eventMessage";
  private static final EventType EVENT_TYPE = EventType.GAME;

  @Mock
  private AppEvent appEvent;
  private List<String> datastore;

  private EventRepository eventRepository;

  @BeforeEach
  void setUp() {
    datastore = new ArrayList<>();
    eventRepository = new EventRepository(datastore);
  }

  @Test
  void givenAppEvent_whenOnApplicationEvent_thenAddEventToStore() {
    given(appEvent.getMessage()).willReturn(MESSAGE);
    given(appEvent.getType()).willReturn(EVENT_TYPE);

    eventRepository.onApplicationEvent(appEvent);

    assertThat(datastore.get(0)).isEqualTo("[%s] : %s".formatted(EVENT_TYPE, MESSAGE));
  }
}