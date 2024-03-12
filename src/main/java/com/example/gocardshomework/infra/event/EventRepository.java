package com.example.gocardshomework.infra.event;

import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventRepository implements ApplicationListener<AppEvent> {

  private final List<String> datastore;

  public EventRepository(List<String> datastore) {
    this.datastore = datastore;
  }
  @Override
  public void onApplicationEvent(AppEvent event) {
    datastore.add("[%s] : %s".formatted(event.getType(), event.getMessage()));
  }

  public List<String> getAllEvents() {
    return datastore;
  }
}
