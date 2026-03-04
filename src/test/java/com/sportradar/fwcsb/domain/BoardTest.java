package com.sportradar.fwcsb.domain;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.team.AwayTeam;
import com.sportradar.fwcsb.domain.game.team.HomeTeam;
import com.sportradar.fwcsb.domain.game.team.Team;
import com.sportradar.fwcsb.service.Service;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
    void testFinishGame() {
        // given
        Team home = new HomeTeam("Mexico");
        Team away = new AwayTeam("Canada");
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
        Team home = new HomeTeam("Mexico");
        Team away = new AwayTeam("Canada");
        TeamScore homeTeamScore = new TeamScore(home, 2);
        TeamScore awayTeamScore = new TeamScore(away, 1);
        // when
        board.updateScore();
        // then
        Mockito.verify(service).updateScore();
    }

    @Test
    void testTotalSummary() {
        // when
        board.totalSummary();
        // then
        Mockito.verify(service).totalSummary();
    }

}
