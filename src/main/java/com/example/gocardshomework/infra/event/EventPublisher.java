package com.example.gocardshomework.infra.event;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

  private final ApplicationEventPublisher eventPublisher;

  public EventPublisher(ApplicationEventPublisher eventPublisher) {
    this.eventPublisher = eventPublisher;
  }

  public void publishGameEvent(String message) {
    eventPublisher.publishEvent(new AppEvent(this, message, EventType.GAME));
  }

  public void publishPlayerEvent(String message) {
    eventPublisher.publishEvent(new AppEvent(this, message, EventType.PLAYER));
  }

  public void publishDeckEvent(String message) {
    eventPublisher.publishEvent(new AppEvent(this, message, EventType.DECK));
  }
}
