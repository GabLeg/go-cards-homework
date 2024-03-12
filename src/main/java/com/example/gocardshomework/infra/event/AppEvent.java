package com.example.gocardshomework.infra.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class AppEvent extends ApplicationEvent {

  private final String message;
  private final EventType type;

  public AppEvent(Object source, String message, EventType type) {
    super(source);
    this.message = message;
    this.type = type;
  }

}
