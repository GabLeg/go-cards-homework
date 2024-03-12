package com.example.gocardshomework.domain.statistics;

import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.game.Game;
import com.example.gocardshomework.infra.GameRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class StatisticsService {

  private final GameRepository gameRepository;

  public StatisticsService(GameRepository gameRepository) {
    this.gameRepository = gameRepository;
  }

  public List<PlayerStatistics> calculatePlayersStatistics(String gameId) {
    List<PlayerStatistics> playerStatistics = new ArrayList<>();
    Game game = gameRepository.getGameById(gameId);
    Map<String, List<Card>> players = new HashMap<>(game.getPlayers());
    Set<String> playerIds = players.keySet();
    for (String playerId : playerIds) {
      int cardsValue = sumCardsValue(players.get(playerId));
      playerStatistics.add(new PlayerStatistics(playerId, cardsValue));
    }
    return sortFromBestToWorst(playerStatistics);
  }

  public String countUndealtCards(String gameId) {
    int spadesCount = 0;
    int clubsCount = 0;
    int diamondsCount = 0;
    int hearthsCount = 0;
    Game game = gameRepository.getGameById(gameId);
    List<Card> undealtCards = new ArrayList<>(game.getAvailableCards());
    for (Card undealtCard : undealtCards) {
      switch (undealtCard.getSuit()) {
        case SPADES -> spadesCount++;
        case CLUBS -> clubsCount++;
        case DIAMONDS -> diamondsCount++;
        default -> hearthsCount++;
      }
    }

    return "%d spades, %d clubs, %d diamonds, %d hearths".formatted(spadesCount, clubsCount, diamondsCount, hearthsCount);
  }

  private int sumCardsValue(List<Card> cards) {
    return cards.stream().mapToInt(Card::getIntValue).sum();
  }

  private List<PlayerStatistics> sortFromBestToWorst(List<PlayerStatistics> playerStatistics) {
    return playerStatistics.stream().sorted(Comparator.comparing(PlayerStatistics::getCardsValue).reversed()).collect(Collectors.toList());
  }
}
