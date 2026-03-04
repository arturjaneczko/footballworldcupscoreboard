package com.sportradar.fwcsb.service;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.match.Match;
import com.sportradar.fwcsb.domain.game.match.Summary;
import com.sportradar.fwcsb.domain.game.team.AwayTeam;
import com.sportradar.fwcsb.domain.game.team.HomeTeam;
import com.sportradar.fwcsb.domain.game.team.Team;
import com.sportradar.fwcsb.infra.Storage;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class Service {

    private final Storage storage;

    public Service(final Storage storage) {
        this.storage = storage;
    }

    public boolean startGame(final Game game) {
        TeamScore home = new TeamScore(game.getHomeTeam(), 0);
        TeamScore away = new TeamScore(game.getAwayTeam(), 0);
        Match match = new Match(home, away);
        return storage.updateScoreboard(game, match);
    }

    public boolean finishGame(final Game game) {
        return storage.removeFromScoreboard(game);
    }

    public boolean updateScore(final Game game, final Match match) {
        return storage.updateScoreboard(game, match);
    }

    public List<Summary> totalSummary() {
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
        return Arrays.asList(summaryD, summaryB, summaryA, summaryE, summaryC);
    }

    public Optional<Game> findGame(final Team home, final Team away) {
        return null;
    }

    public Match getMatch(final Game game) {
        return null;
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
