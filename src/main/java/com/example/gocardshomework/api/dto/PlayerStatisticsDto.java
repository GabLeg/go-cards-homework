package com.example.gocardshomework.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PlayerStatisticsDto {

  private String playerId;
  private int cardsValue;
}
