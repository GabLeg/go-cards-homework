package com.example.gocardshomework.config.mapper;

import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

@Component
public class ModelMapper {

  private final org.modelmapper.ModelMapper mapper;

  public ModelMapper() {
    mapper = new org.modelmapper.ModelMapper();
    mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
  }

  public <T> T map(Object obj, Class<T> destinationClass) {
    return mapper.map(obj, destinationClass);
  }
}
