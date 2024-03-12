package com.example.gocardshomework.api.controllers;

import com.example.gocardshomework.api.dto.PlayerStatisticsDto;
import com.example.gocardshomework.config.mapper.ModelMapper;
import com.example.gocardshomework.domain.statistics.StatisticsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/game-statistics/{gameId}")
public class GameStatisticsController {

  private final StatisticsService statisticsService;
  private final ModelMapper mapper;

  public GameStatisticsController(StatisticsService statisticsService, ModelMapper mapper) {
    this.statisticsService = statisticsService;
    this.mapper = mapper;
  }

  @GetMapping("/players")
  public List<PlayerStatisticsDto> getPlayersStatistics(@PathVariable("gameId") String gameId) {
    return mapper.mapList(statisticsService.calculatePlayersStatistics(gameId), PlayerStatisticsDto.class);
  }
}
