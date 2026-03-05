package com.sportradar.fwcsb.domain;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.match.Match;
import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.match.Summary;
import com.sportradar.fwcsb.domain.game.team.AwayTeam;
import com.sportradar.fwcsb.domain.game.team.HomeTeam;
import com.sportradar.fwcsb.domain.game.team.Team;
import com.sportradar.fwcsb.domain.rules.ex.GameAlreadyStartedException;
import com.sportradar.fwcsb.domain.rules.ex.NegativeScoreException;
import com.sportradar.fwcsb.domain.rules.ex.TeamSameOriginException;
import com.sportradar.fwcsb.service.Service;
import org.assertj.core.api.AbstractThrowableAssert;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class BoardTest {

    @InjectMocks
    private Board board;
    @Mock
    private Service service;

    @Test
    void testStartGame() {
        // given
        Team home = new HomeTeam("Mexico");
        Team away = new AwayTeam("Canada");
        // when
        board.startGame(home, away);
        // then
        ArgumentCaptor<Game> captor = ArgumentCaptor.forClass(Game.class);
        Mockito.verify(service).startGame(captor.capture());
        Game game = captor.getValue();
        Assertions.assertThat(game.getHomeTeam()).isEqualTo(home);
        Assertions.assertThat(game.getAwayTeam()).isEqualTo(away);
    }

    @Test
    void testStartStartedGameBusinessRule() {
        // given
        Team home = new HomeTeam("Spain");
        Team away = new AwayTeam("Brazil");
        Game game = new Game(home, away);
        Mockito.when(service.findGame(home, away)).thenReturn(Optional.of(game));
        // when
        AbstractThrowableAssert throwableAssert = Assertions.assertThatThrownBy(() -> board.startGame(home, away));
        // then
        throwableAssert.isInstanceOf(RuntimeException.class);
        throwableAssert.cause().isInstanceOf(GameAlreadyStartedException.class);
        throwableAssert.hasMessageContaining("[ BusinessRuleException ]: Game already started! Spain - Brazil");
    }

    @Test
    void testSameTeamOriginBusinessRule() {
        // given
        Team home = new HomeTeam("Germany");
        Team away = new HomeTeam("France");
        Game game = new Game(home, away);
        // when
        AbstractThrowableAssert throwableAssert = Assertions.assertThatThrownBy(() -> board.startGame(home, away));
        // then
        throwableAssert.isInstanceOf(RuntimeException.class);
        throwableAssert.cause().isInstanceOf(TeamSameOriginException.class);
        throwableAssert.hasMessageContaining("[ BusinessRuleException ]: Teams have the same origin!");
    }

    @Test
    void testFinishGame() {
        // given
        Team home = new HomeTeam("Uruguay");
        Team away = new AwayTeam("Italy");
        Game game = new Game(home, away);
        Mockito.when(service.findGame(home, away)).thenReturn(Optional.of(game));
        // when
        board.finishGame(home, away);
        // then
        Mockito.verify(service).finishGame(game);
    }

    @Test
    void testUpdateScore() {
        // given
        Team home = new HomeTeam("Argentina");
        Team away = new AwayTeam("Australia");
        TeamScore homeTeamScore = new TeamScore(home, 2);
        TeamScore awayTeamScore = new TeamScore(away, 1);
        Game game = new Game(home, away);
        Mockito.when(service.findGame(home, away)).thenReturn(Optional.of(game));
        Match match = new Match(new TeamScore(home, 0), new TeamScore(away, 0));
        Mockito.when(service.getMatch(game)).thenReturn(match);
        // when
        board.updateScore(homeTeamScore, awayTeamScore);
        // then
        Assertions.assertThat(match.getHome().score()).isEqualTo(2);
        Assertions.assertThat(match.getAway().score()).isEqualTo(1);
        Mockito.verify(service).updateScore(game, match);
    }

    @Test
    void testUpdateNegativeScoreBusinessRule() {
        // given
        Team home = new HomeTeam("Mexico");
        Team away = new AwayTeam("Canada");
        TeamScore homeTeamScore = new TeamScore(home, -2);
        TeamScore awayTeamScore = new TeamScore(away, -1);
        Game game = new Game(home, away);
        Mockito.when(service.findGame(home, away)).thenReturn(Optional.of(game));
        Match match = new Match(new TeamScore(home, 0), new TeamScore(away, 0));
        Mockito.when(service.getMatch(game)).thenReturn(match);
        // when
        AbstractThrowableAssert throwableAssert = Assertions.assertThatThrownBy(() -> board.updateScore(homeTeamScore, awayTeamScore));
        // then
        throwableAssert.isInstanceOf(RuntimeException.class);
        throwableAssert.cause().isInstanceOf(NegativeScoreException.class);
        throwableAssert.hasMessageContaining("[ BusinessRuleException ]: Negative score!");
    }

    @Test
    void testTotalSummary() {
        // given
        List<Summary> summary = new ArrayList<>();
        Mockito.when(service.totalSummary()).thenReturn(summary);
        // when
        List<Summary> result = board.totalSummary();
        // then
        Assertions.assertThat(result).isEqualTo(summary);
    }

}
