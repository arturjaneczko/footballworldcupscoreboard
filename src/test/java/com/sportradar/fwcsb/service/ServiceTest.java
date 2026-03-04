package com.sportradar.fwcsb.service;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.match.Match;
import com.sportradar.fwcsb.domain.game.match.Summary;
import com.sportradar.fwcsb.domain.game.team.AwayTeam;
import com.sportradar.fwcsb.domain.game.team.HomeTeam;
import com.sportradar.fwcsb.domain.game.team.Team;
import com.sportradar.fwcsb.infra.Storage;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ServiceTest {

    @InjectMocks
    private Service service;
    @Mock
    private Storage storage;

    @Test
    void testStartGame() {
        // given
        Team mexico = home("Mexico");
        Team canada = away("Canada");
        Game game = new Game(mexico, canada);
        // when
        service.startGame(game);
        // then
        ArgumentCaptor<Match> captor = ArgumentCaptor.forClass(Match.class);
        Mockito.verify(storage).updateScoreboard(Mockito.eq(game), captor.capture());
        Match match = captor.getValue();
        Assertions.assertThat(match).extracting(Match::getHome).extracting(TeamScore::score).isEqualTo(0);
        Assertions.assertThat(match).extracting(Match::getAway).extracting(TeamScore::score).isEqualTo(0);
    }

    @Test
    void testFinishGame() {
        // given
        Team mexico = home("Mexico");
        Team canada = away("Canada");
        Game game = new Game(mexico, canada);
        // when
        service.finishGame(game);
        // then
        Mockito.verify(storage).removeFromScoreboard(game);
    }

    @Test
    void testUpdateScore() {
        // given
        Team mexico = home("Mexico");
        Team canada = away("Canada");
        Game game = new Game(mexico, canada);
        Match match = giveMatch(mexico, 1, canada, 1);
        // when
        service.updateScore(game, match);
        // then
        Mockito.verify(storage).updateScoreboard(game, match);
    }

    @Test
    void testTotalSummary() {
        // given
        Team mexico = home("Mexico");
        Team spain = home("Spain");
        Team germany = home("Germany");
        Team uruguay = home("Uruguay");
        Team argentina = home("Argentina");

        Team canada = away("Canada");
        Team brazil = away("Brazil");
        Team france = away("France");
        Team italy = away("Italy");
        Team australia = away("Australia");

        Match matchA = giveMatch(mexico, 0, canada, 5);
        Match matchB = giveMatch(spain, 10, brazil, 2);
        Match matchC = giveMatch(germany, 2, france, 2);
        Match matchD = giveMatch(uruguay, 6, italy, 6);
        Match matchE = giveMatch(argentina, 3, australia, 1);

        Summary summaryA = matchA.getSummary();
        Summary summaryB = matchB.getSummary();
        Summary summaryC = matchC.getSummary();
        Summary summaryD = matchD.getSummary();
        Summary summaryE = matchE.getSummary();
        // when
        List<Summary> result = service.totalSummary();
        // then
        Assertions.assertThat(result).containsExactly(summaryD, summaryB, summaryA, summaryE, summaryC);
    }

    private static Team home(String name) {
        return new HomeTeam(name);
    }

    private static Team away(String name) {
        return new AwayTeam(name);
    }

    private static Match giveMatch(Team home, int homeScore, Team away, int awayScore) {
        TeamScore homeTeamScore = new TeamScore(home, homeScore);
        TeamScore awayTeamScore = new TeamScore(away, awayScore);
        return new Match(homeTeamScore, awayTeamScore);
    }

}