package com.example.gocardshomework.api.controllers;

import com.example.gocardshomework.infra.event.EventRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
public class EventController {

  private final EventRepository eventRepository;

  public EventController(EventRepository eventRepository) {

    this.eventRepository = eventRepository;
  }

  @GetMapping
  public List<String> getAllEvents() {
    return eventRepository.getAllEvents();
  }
}
