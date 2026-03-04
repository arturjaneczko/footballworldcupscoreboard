package com.sportradar.fwcsb.service;

import com.sportradar.fwcsb.domain.game.Game;
import com.sportradar.fwcsb.domain.game.TeamScore;
import com.sportradar.fwcsb.domain.game.match.Match;
import com.sportradar.fwcsb.domain.game.match.Summary;
import com.sportradar.fwcsb.domain.game.team.Team;
import com.sportradar.fwcsb.infra.Storage;

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
        return false;
    }

    public boolean updateScore(final Game game, final Match match) {
        return false;
    }

    public List<Summary> totalSummary() {
        return null;
    }

    public Optional<Game> findGame(final Team home, final Team away) {
        return null;
    }

    public Match getMatch(final Game game) {
        return null;
    }
}
