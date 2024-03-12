package com.example.gocardshomework.domain.statistics;

import com.example.gocardshomework.domain.cards.Card;
import com.example.gocardshomework.domain.cards.Suit;
import com.example.gocardshomework.domain.cards.Value;
import com.example.gocardshomework.domain.game.Game;
import com.example.gocardshomework.infra.GameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class StatisticsServiceTest {

  private static final List<Card> LOWEST_HAND = List.of(new Card(Value.ACE, Suit.SPADES), new Card(Value.TWO, Suit.DIAMONDS));
  private static final List<Card> HIGHEST_HAND = List.of(new Card(Value.KING, Suit.SPADES), new Card(Value.QUEEN, Suit.CLUBS));
  private static final String GAME_ID = "gameId5677";
  private static final String PLAYER_ID1 = "playerId1";
  private static final String PLAYER_ID2 = "playerId2";


  @Mock
  private Game game;
  @Mock
  private GameRepository gameRepository;

  private StatisticsService statisticsService;

  @BeforeEach
  void setUp() {
    statisticsService = new StatisticsService(gameRepository);
  }

  @Test
  void givenGameId_whenCalculatePlayersStatistics_thenReturnAllPlayerStatistics() {
    Map<String, List<Card>> players = Map.of(PLAYER_ID1, LOWEST_HAND, PLAYER_ID2, HIGHEST_HAND);
    given(gameRepository.getGameById(GAME_ID)).willReturn(game);
    given(game.getPlayers()).willReturn(players);

    List<PlayerStatistics> playerStatistics = statisticsService.calculatePlayersStatistics(GAME_ID);

    assertThat(playerStatistics.get(0).getPlayerId()).isEqualTo(PLAYER_ID2);
    assertThat(playerStatistics.get(0).getCardsValue()).isEqualTo(Value.KING.getValue() + Value.QUEEN.getValue());
    assertThat(playerStatistics.get(1).getPlayerId()).isEqualTo(PLAYER_ID1);
    assertThat(playerStatistics.get(1).getCardsValue()).isEqualTo(Value.ACE.getValue() + Value.TWO.getValue());
  }

  @Test
  void givenGameIdWithUndealtCards_whenCountUndealtCards_thenReturnUndeltStatistics() {
    List<Card> undealtCards = List.of(new Card(Value.ACE, Suit.SPADES),
                                      new Card(Value.ACE, Suit.CLUBS),
                                      new Card(Value.ACE, Suit.DIAMONDS),
                                      new Card(Value.TWO, Suit.DIAMONDS));
    given(gameRepository.getGameById(GAME_ID)).willReturn(game);
    given(game.getAvailableCards()).willReturn(undealtCards);

    String result = statisticsService.countUndealtCards(GAME_ID);

    assertThat(result).isEqualTo("1 spades, 1 clubs, 2 diamonds, 0 hearths");
  }
}